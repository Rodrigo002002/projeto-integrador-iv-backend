package com.apiathletevision.apiathletevision.services.impl;

import com.apiathletevision.apiathletevision.dtos.entities.ModalidadeDTO;
import com.apiathletevision.apiathletevision.dtos.entities.PlanoDTO;
import com.apiathletevision.apiathletevision.dtos.entities.TurmaDTO;
import com.apiathletevision.apiathletevision.dtos.response.PageDTO;
import com.apiathletevision.apiathletevision.dtos.select2.Select2OptionsDTO;
import com.apiathletevision.apiathletevision.entities.Modalidade;
import com.apiathletevision.apiathletevision.entities.Plano;
import com.apiathletevision.apiathletevision.entities.Turma;
import com.apiathletevision.apiathletevision.exeptions.BadRequestException;
import com.apiathletevision.apiathletevision.mappers.ModalidadeMapper;
import com.apiathletevision.apiathletevision.mappers.PlanoMapper;
import com.apiathletevision.apiathletevision.mappers.TurmaMapper;
import com.apiathletevision.apiathletevision.repositories.ModalidadeRepository;
import com.apiathletevision.apiathletevision.repositories.PlanoRepository;
import com.apiathletevision.apiathletevision.repositories.TurmaRepository;
import com.apiathletevision.apiathletevision.services.ModalidadeService;
import com.apiathletevision.apiathletevision.services.specifications.ModalidadeSpecification;
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
public class ModalidadeServiceImpl implements ModalidadeService {

    private final ModalidadeRepository modalidadeRepository;
    private final ModalidadeMapper modalidadeMapper;
    private final PlanoRepository planoRepository;
    private final PlanoMapper planoMapper;
    private final TurmaRepository turmaRepository;
    private final TurmaMapper turmaMapper;

    @Override
    public PageDTO<Modalidade, ModalidadeDTO> findAll(int pageNo, int pageSize, String search, Boolean status) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("nome"));
        Specification<Modalidade> specification = ModalidadeSpecification.getFilters(search, status);
        Page<Modalidade> modalidadePage = modalidadeRepository.findAll(specification, pageable);
        return new PageDTO<>(modalidadePage, modalidadeMapper::toDto);
    }

    @Override
    @Transactional
    public ModalidadeDTO create(ModalidadeDTO modalidadeDTO) {
        Modalidade modalidade = modalidadeMapper.toEntity(modalidadeDTO);

        modalidade = modalidadeRepository.save(modalidade);
        return modalidadeMapper.toDto(modalidade);
    }

    @Override
    public ModalidadeDTO update(ModalidadeDTO modalidadeDTO) throws BadRequestException {
        Modalidade modalidade = getModalidade(modalidadeDTO.getId());

        modalidadeMapper.partialUpdate(modalidadeDTO, modalidade);

        modalidade = modalidadeRepository.save(modalidade);
        return modalidadeMapper.toDto(modalidade);
    }

    @Override
    public ModalidadeDTO findById(int id) throws BadRequestException {
        Modalidade modalidade = getModalidade(id);
        return modalidadeMapper.toDto(modalidade);
    }

    @Override
    public void delete(int id) throws BadRequestException {
        Modalidade modalidade = getModalidade(id);
        if (!modalidade.getStatus()) {
            modalidadeRepository.delete(modalidade);
        }
        throw new BadRequestException("Gestor ainda ativo");
    }

    @Override
    public void changeStatus(int id, boolean status) {
        Optional<Modalidade> optionalModalidade = modalidadeRepository.findById(id);
        if (optionalModalidade.isPresent()) {
            Modalidade modalidade = optionalModalidade.get();
            modalidade.setStatus(status);
            modalidadeRepository.save(modalidade);
        }
    }

    private Modalidade getModalidade(int id) throws BadRequestException {
        return modalidadeRepository.findById(id).orElseThrow(() ->
                new BadRequestException("Modalidade com o ID: " + id + " não encontrado"));
    }

    @Override
    public List<Select2OptionsDTO> findAllToSelect2(String searchTerm) {
        List<Modalidade> list;

        if (StringUtils.isBlank(searchTerm)) {
            list = modalidadeRepository.findAllByStatusIsTrue();
        } else {
            list = modalidadeRepository.findByStatusIsTrueAndNomeContainingIgnoreCaseOrderByNome(searchTerm);
        }

        return list
                .stream()
                .map(data -> new Select2OptionsDTO(data.getId(), data.getNome()))
                .collect(Collectors.toList());
    }

    @Override
    public List<PlanoDTO> findAllPlanoByModalidadeId(int id) {
        List<Plano> planos = planoRepository.findAllByModalidades_id(id);
        if (planos.isEmpty()) {
            throw new BadRequestException("Nenhum plano encontrado");
        }
        return planoMapper.toDtoList(planos);
    }

    @Override
    public List<TurmaDTO> findAllTurmaByModalidadeId(int id) {
        List<Turma> turmas = turmaRepository.findAllByModalidade_id(id);
        if (turmas.isEmpty()) {
            throw new BadRequestException("Nenhuma turma encontrada");
        }
        return turmaMapper.toDtoList(turmas);
    }
}