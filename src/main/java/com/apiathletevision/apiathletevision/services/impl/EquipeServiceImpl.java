package com.apiathletevision.apiathletevision.services.impl;

import com.apiathletevision.apiathletevision.dtos.entities.EquipeDTO;
import com.apiathletevision.apiathletevision.dtos.response.PageDTO;
import com.apiathletevision.apiathletevision.dtos.select2.Select2OptionsDTO;
import com.apiathletevision.apiathletevision.entities.Aluno;
import com.apiathletevision.apiathletevision.entities.Equipe;
import com.apiathletevision.apiathletevision.exeptions.BadRequestException;
import com.apiathletevision.apiathletevision.mappers.EquipeMapper;
import com.apiathletevision.apiathletevision.repositories.AlunoRepository;
import com.apiathletevision.apiathletevision.repositories.EquipeRepository;
import com.apiathletevision.apiathletevision.services.EquipeService;
import com.apiathletevision.apiathletevision.services.specifications.EquipeSpecification;
import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class EquipeServiceImpl implements EquipeService {

    private final EquipeRepository equipeRepository;
    private final EquipeMapper equipeMapper;
    private final AlunoRepository alunoRepository;

    public PageDTO<Equipe, EquipeDTO> findAll(int pageNo, int pageSize, String search, Boolean status) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("tipo"));
        Specification<Equipe> specification = EquipeSpecification.getFilters(search, status);
        Page<Equipe> equipePage = equipeRepository.findAll(specification, pageable);
        return new PageDTO<>(equipePage, equipeMapper::toDto);
    }

    @Override
    public EquipeDTO findById(int id) throws BadRequestException {
        Equipe equipe = getEquipe(id);
        return equipeMapper.toDto(equipe);
    }

    @Override
    @Transactional
    public EquipeDTO create(EquipeDTO equipeDTO) {
        Equipe equipe = equipeMapper.toEntity(equipeDTO);

        setCreateAssociations(equipeDTO, equipe);

        equipe = equipeRepository.save(equipe);
        return equipeMapper.toDto(equipe);
    }

    @Override
    @Transactional
    public EquipeDTO update(EquipeDTO equipeDTO) throws BadRequestException {
        Equipe equipe = getEquipe(equipeDTO.getId());
        equipeMapper.partialUpdate(equipeDTO, equipe);

        setCreateAssociations(equipeDTO, equipe);

        equipe = equipeRepository.save(equipe);

        return equipeMapper.toDto(equipe);
    }

    @Override
    public void delete(int id) throws BadRequestException {
        Equipe equipe = getEquipe(id);
        equipeRepository.delete(equipe);
    }

    @Override
    public void changeStatus(int id, boolean status) {
        Optional<Equipe> optionalEquipe = equipeRepository.findById(id);
        if (optionalEquipe.isPresent()) {
            Equipe equipe = optionalEquipe.get();
            equipe.setStatus(status);
            equipeRepository.save(equipe);
        }
    }

    @Override
    public List<Select2OptionsDTO> findAllToSelect2(String searchTerm) {
        List<Equipe> list;

        if (StringUtils.isBlank(searchTerm)) {
            list = equipeRepository.findAllByStatusIsTrue();
        } else {
            list = equipeRepository.findByStatusIsTrueAndNomeContainingIgnoreCaseOrderByNome(searchTerm);
        }

        return list
                .stream()
                .map(data -> new Select2OptionsDTO(data.getId(), data.getNome()))
                .collect(Collectors.toList());
    }

    private Equipe getEquipe(int id) throws BadRequestException {
        return equipeRepository.findById(id).orElseThrow(() ->
                new BadRequestException("Equipe com o ID: " + id + " não encontrado"));
    }

    private void setCreateAssociations(EquipeDTO equipeDTO, Equipe equipe) {
        if (equipe.getAlunos() != null) {
            List<Aluno> alunos = alunoRepository.findAllById(equipeDTO.getAlunosIds());
            equipe.setAlunos(alunos);
        }
    }
}