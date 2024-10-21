package com.apiathletevision.apiathletevision.services.impl;

import com.apiathletevision.apiathletevision.dtos.entities.TipoServicoDTO;
import com.apiathletevision.apiathletevision.dtos.response.PageDTO;
import com.apiathletevision.apiathletevision.dtos.select2.Select2OptionsDTO;
import com.apiathletevision.apiathletevision.entities.TipoServico;
import com.apiathletevision.apiathletevision.exeptions.BadRequestException;
import com.apiathletevision.apiathletevision.mappers.TipoServicoMapper;
import com.apiathletevision.apiathletevision.repositories.TipoServicoRepository;
import com.apiathletevision.apiathletevision.services.TipoServicoService;
import com.apiathletevision.apiathletevision.services.specifications.TipoServicoSpecification;
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
public class TipoServicoServiceImpl implements TipoServicoService {

    private final TipoServicoRepository tipoServicoRepository;
    private final TipoServicoMapper tipoServicoMapper;

    public PageDTO<TipoServico, TipoServicoDTO> findAll(int pageNo, int pageSize, String search, Boolean status) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("tipo"));
        Specification<TipoServico> specification = TipoServicoSpecification.getFilters(search, status);
        Page<TipoServico> tipoServicoPage = tipoServicoRepository.findAll(specification, pageable);
        return new PageDTO<>(tipoServicoPage, tipoServicoMapper::toDto);
    }

    @Override
    public TipoServicoDTO findById(int id) throws BadRequestException {
        TipoServico tipoServico = getTipoServico(id);
        return tipoServicoMapper.toDto(tipoServico);
    }

    @Override
    @Transactional
    public TipoServicoDTO create(TipoServicoDTO tipoServicoDTO) {
        TipoServico tipoServico = tipoServicoMapper.toEntity(tipoServicoDTO);
        tipoServico = tipoServicoRepository.save(tipoServico);
        return tipoServicoMapper.toDto(tipoServico);
    }

    @Override
    @Transactional
    public TipoServicoDTO update(TipoServicoDTO tipoServicoDTO) throws BadRequestException {
        TipoServico tipoServico = getTipoServico(tipoServicoDTO.getId());
        tipoServicoMapper.partialUpdate(tipoServicoDTO, tipoServico);
        tipoServico = tipoServicoRepository.save(tipoServico);
        return tipoServicoMapper.toDto(tipoServico);
    }

    @Override
    public void delete(int id) throws BadRequestException {
        TipoServico tipoServico = getTipoServico(id);
        tipoServicoRepository.delete(tipoServico);
    }

    @Override
    public void changeStatus(int id, boolean status) {
        Optional<TipoServico> optionalTipoServico = tipoServicoRepository.findById(id);
        if (optionalTipoServico.isPresent()) {
            TipoServico tipoServico = optionalTipoServico.get();
            tipoServico.setStatus(status);
            tipoServicoRepository.save(tipoServico);
        }
    }

    @Override
    public List<Select2OptionsDTO> findAllToSelect2(String searchTerm) {
        List<TipoServico> list;

        if (StringUtils.isBlank(searchTerm)) {
            list = tipoServicoRepository.findAllByStatusIsTrue();
        } else {
            list = tipoServicoRepository.findByStatusIsTrueAndTipoContainingIgnoreCaseOrderByTipo(searchTerm);
        }

        return list
                .stream()
                .map(data -> new Select2OptionsDTO(data.getId(), data.getTipo()))
                .collect(Collectors.toList());
    }

    private TipoServico getTipoServico(int id) throws BadRequestException {
        return tipoServicoRepository.findById(id).orElseThrow(() ->
                new BadRequestException("TipoServico com o ID: " + id + " não encontrado"));
    }
}
