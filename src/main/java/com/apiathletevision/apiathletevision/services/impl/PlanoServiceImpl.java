package com.apiathletevision.apiathletevision.services.impl;

import com.apiathletevision.apiathletevision.dtos.entities.PlanoDTO;
import com.apiathletevision.apiathletevision.dtos.response.PageDTO;
import com.apiathletevision.apiathletevision.dtos.select2.Select2OptionsDTO;
import com.apiathletevision.apiathletevision.entities.Modalidade;
import com.apiathletevision.apiathletevision.entities.Pagamento;
import com.apiathletevision.apiathletevision.entities.Plano;
import com.apiathletevision.apiathletevision.exeptions.BadRequestException;
import com.apiathletevision.apiathletevision.mappers.PlanoMapper;
import com.apiathletevision.apiathletevision.repositories.ModalidadeRepository;
import com.apiathletevision.apiathletevision.repositories.PagamentoRepository;
import com.apiathletevision.apiathletevision.repositories.PlanoRepository;
import com.apiathletevision.apiathletevision.services.PlanoService;
import com.apiathletevision.apiathletevision.services.specifications.PlanoSpecification;
import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PlanoServiceImpl implements PlanoService {

    private final PlanoRepository planoRepository;
    private final PlanoMapper planoMapper;
    private final ModalidadeRepository modalidadeRepository;
    private final PagamentoRepository pagamentoRepository;

    @Override
    public PageDTO<Plano, PlanoDTO> findAll(int pageNo, int pageSize, String search, Boolean status) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("nome"));
        Specification<Plano> specification = PlanoSpecification.getFilters(search, status);
        Page<Plano> planoPage = planoRepository.findAll(specification, pageable);
        return new PageDTO<>(planoPage, planoMapper::toDto);
    }

    @Override
    @Transactional
    public PlanoDTO create(PlanoDTO planoDTO) {
        Plano plano = planoMapper.toEntity(planoDTO);

        setCreateAssociations(planoDTO, plano);

        plano = planoRepository.save(plano);
        return planoMapper.toDto(plano);
    }

    @Override
    public PlanoDTO update(PlanoDTO planoDTO) throws BadRequestException {
        Plano plano = getPlano(planoDTO.getId());

        planoMapper.partialUpdate(planoDTO, plano);

        setCreateAssociations(planoDTO, plano);

        plano = planoRepository.save(plano);
        return planoMapper.toDto(plano);
    }

    @Override
    public PlanoDTO findById(int id) throws BadRequestException {
        Plano plano = getPlano(id);
        return planoMapper.toDto(plano);
    }

    @Override
    public void delete(int id) throws BadRequestException {
        Plano plano = getPlano(id);
        if (!plano.getStatus()) {
            planoRepository.delete(plano);
        }
        throw new BadRequestException("Plano ainda ativo");
    }

    @Override
    public void changeStatus(int id, boolean status) {
        Optional<Plano> optionalPlano = planoRepository.findById(id);
        if (optionalPlano.isPresent()) {
            Plano plano = optionalPlano.get();
            plano.setStatus(status);
            planoRepository.save(plano);
        }
    }

    private Plano getPlano(int id) throws BadRequestException {
        return planoRepository.findById(id).orElseThrow(() ->
                new BadRequestException("Plano com o ID: " + id + " não encontrado"));
    }

    @Override
    public List<Select2OptionsDTO> findAllToSelect2(String searchTerm) {
        List<Plano> list;

        if (StringUtils.isBlank(searchTerm)) {
            list = planoRepository.findAllByStatusIsTrue();
        } else {
            list = planoRepository.findByStatusIsTrueAndNomeContainingIgnoreCaseOrderByNome(searchTerm);
        }

        return list
                .stream()
                .map(data -> new Select2OptionsDTO(data.getId(), data.getNome()))
                .collect(Collectors.toList());
    }

    private void setCreateAssociations(PlanoDTO planoDTO, Plano plano) {
        if (planoDTO.getModalidadesIds() != null) {
            List<Modalidade> modalidades = modalidadeRepository.findAllById(planoDTO.getModalidadesIds());
            plano.setModalidades(modalidades);
        }

        if (planoDTO.getPagamentosIds() != null) {
            List<Pagamento> pagamentos = pagamentoRepository.findAllById(planoDTO.getPagamentosIds());
            plano.setPagamentos(pagamentos);
        }
    }
}