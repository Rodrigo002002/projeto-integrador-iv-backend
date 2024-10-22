package com.apiathletevision.apiathletevision.services.impl;

import com.apiathletevision.apiathletevision.dtos.entities.ResponsavelDTO;
import com.apiathletevision.apiathletevision.dtos.response.PageDTO;
import com.apiathletevision.apiathletevision.entities.Aluno;
import com.apiathletevision.apiathletevision.entities.Responsavel;
import com.apiathletevision.apiathletevision.exeptions.BadRequestException;
import com.apiathletevision.apiathletevision.mappers.ResponsavelMapper;
import com.apiathletevision.apiathletevision.repositories.AlunoRepository;
import com.apiathletevision.apiathletevision.repositories.ResponsavelRepository;
import com.apiathletevision.apiathletevision.services.ResponsavelService;
import com.apiathletevision.apiathletevision.services.specifications.ResponsavelSpecification;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ResponsavelServiceImpl implements ResponsavelService {

    private final ResponsavelRepository responsavelRepository;
    private final ResponsavelMapper responsavelMapper;
    private final AlunoRepository alunoRepository;

    @Override
    public PageDTO<Responsavel, ResponsavelDTO> findAll(int pageNo, int pageSize, String search, Boolean status) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("nome"));
        Specification<Responsavel> specification = ResponsavelSpecification.getFilters(search, status);
        Page<Responsavel> responsavelPage = responsavelRepository.findAll(specification, pageable);
        return new PageDTO<>(responsavelPage, responsavelMapper::toDto);
    }

    @Override
    @Transactional
    public ResponsavelDTO create(ResponsavelDTO responsavelDTO) {
        Responsavel responsavel = responsavelMapper.toEntity(responsavelDTO);

        String encryptedPassword = new BCryptPasswordEncoder().encode(responsavelDTO.getPassword());
        responsavel.setPassword(encryptedPassword);
        responsavel.setLogin(responsavelDTO.getLogin());
        responsavel.setStatus(responsavelDTO.getStatus());

        setCreateAssociations(responsavelDTO, responsavel);

        responsavel = responsavelRepository.save(responsavel);
        return responsavelMapper.toDto(responsavel);
    }

    @Override
    public ResponsavelDTO update(ResponsavelDTO responsavelDTO) throws BadRequestException {
        Responsavel responsavel = getResponsavel(responsavelDTO.getId());
        String encryptedPassword = new BCryptPasswordEncoder().encode(responsavelDTO.getPassword());
        responsavelMapper.partialUpdate(responsavelDTO, responsavel);

        responsavel.setPassword(encryptedPassword);
        setCreateAssociations(responsavelDTO, responsavel);

        responsavel = responsavelRepository.save(responsavel);
        return responsavelMapper.toDto(responsavel);
    }

    @Override
    public ResponsavelDTO findById(UUID id) throws BadRequestException {
        Responsavel responsavel = getResponsavel(id);
        return responsavelMapper.toDto(responsavel);
    }

    @Override
    public void delete(UUID id) throws BadRequestException {
        Responsavel responsavel = getResponsavel(id);
        if (!responsavel.getStatus()) {
            responsavelRepository.delete(responsavel);
        }
        throw new BadRequestException("Responsavel ainda ativa");
    }

    @Override
    public void changeStatus(UUID id, boolean status) {
        Optional<Responsavel> optionalResponsavel = responsavelRepository.findById(id);
        if (optionalResponsavel.isPresent()) {
            Responsavel responsavel = optionalResponsavel.get();
            responsavel.setStatus(status);
            responsavelRepository.save(responsavel);
        }
    }

    private Responsavel getResponsavel(UUID id) throws BadRequestException {
        return responsavelRepository.findById(id).orElseThrow(() ->
                new BadRequestException("Responsavel com o ID: " + id + " não encontrado"));
    }

    private void setCreateAssociations(ResponsavelDTO responsavelDTO, Responsavel responsavel) {
        if (!responsavelDTO.getAlunosIds().isEmpty()) {
            List<Aluno> alunos = responsavelDTO.getAlunosIds()
                    .stream()
                    .map(alunoId -> {
                                return alunoRepository.findById(alunoId).orElse(null);
                            }
                    ).toList();

            responsavel.setAlunos(alunos);
        }
    }
}