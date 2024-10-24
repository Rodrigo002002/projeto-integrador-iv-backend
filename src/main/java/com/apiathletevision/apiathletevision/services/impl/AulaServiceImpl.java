package com.apiathletevision.apiathletevision.services.impl;

import com.apiathletevision.apiathletevision.dtos.entities.AlunoDTO;
import com.apiathletevision.apiathletevision.dtos.entities.AulaDTO;
import com.apiathletevision.apiathletevision.dtos.response.PageDTO;
import com.apiathletevision.apiathletevision.entities.Aluno;
import com.apiathletevision.apiathletevision.entities.Aula;
import com.apiathletevision.apiathletevision.entities.Professor;
import com.apiathletevision.apiathletevision.entities.Turma;
import com.apiathletevision.apiathletevision.exeptions.BadRequestException;
import com.apiathletevision.apiathletevision.mappers.AulaMapper;
import com.apiathletevision.apiathletevision.repositories.AlunoRepository;
import com.apiathletevision.apiathletevision.repositories.AulaRepository;
import com.apiathletevision.apiathletevision.repositories.ProfessorRepository;
import com.apiathletevision.apiathletevision.repositories.TurmaRepository;
import com.apiathletevision.apiathletevision.services.AulaService;
import com.apiathletevision.apiathletevision.services.specifications.AulaSpecification;
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

@Service
@AllArgsConstructor
public class AulaServiceImpl implements AulaService {

    private final AulaRepository aulaRepository;
    private final AlunoRepository alunoRepository;
    private final TurmaRepository turmaRepository;
    private final AulaMapper aulaMapper;
    private final ProfessorRepository professorRepository;

    public PageDTO<Aula, AulaDTO> findAll(int pageNo, int pageSize, String search, Boolean status) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("data"));
        Specification<Aula> specification = AulaSpecification.getFilters(search, status);
        Page<Aula> aulaPage = aulaRepository.findAll(specification, pageable);
        return new PageDTO<>(aulaPage, aulaMapper::toDto);
    }

    @Override
    public AulaDTO findById(int id) throws BadRequestException {
        Aula aula = getAula(id);
        return aulaMapper.toDto(aula);
    }

    @Override
    @Transactional
    public AulaDTO create(AulaDTO aulaDTO) {
        Aula aula = aulaMapper.toEntity(aulaDTO);
        setAssociations(aulaDTO, aula);
        aula = aulaRepository.save(aula);
        return aulaMapper.toDto(aula);
    }

    @Override
    @Transactional
    public AulaDTO update(AulaDTO aulaDTO) throws BadRequestException {
        Aula aula = getAula(aulaDTO.getId());
        aulaMapper.partialUpdate(aulaDTO, aula);

        setAssociations(aulaDTO, aula);

        aula = aulaRepository.save(aula);
        return aulaMapper.toDto(aula);
    }

    @Override
    public void delete(int id) throws BadRequestException {
        Aula aula = getAula(id);
        if (!aula.getStatus()) {
            aulaRepository.delete(aula);
        }
        throw new BadRequestException("Aula ainda ativa");
    }

    @Override
    public void changeStatus(int id, boolean status) {
        Optional<Aula> optionalAula = aulaRepository.findById(id);
        if (optionalAula.isPresent()) {
            Aula aula = optionalAula.get();
            aula.setStatus(status);
            aulaRepository.save(aula);
        }
    }

    private Aula getAula(int id) throws BadRequestException {
        return aulaRepository.findById(id).orElseThrow(() ->
                new BadRequestException("Aula com o ID: " + id + " não encontrada"));
    }

    private void setAssociations(AulaDTO aulaDTO, Aula aula) {
        if (aulaDTO.getTurmaId() != null) {
            Optional<Turma> turma = turmaRepository.findById(aulaDTO.getTurmaId());
            aula.setTurma(turma.orElse(null));
        }

        if (aulaDTO.getAlunosPresentesIds() != null && !aulaDTO.getAlunosPresentesIds().isEmpty()) {
            List<Aluno> alunosPresentes = alunoRepository.findAllById(aulaDTO.getAlunosPresentesIds());
            aula.setAlunosPresentes(alunosPresentes);
        }

        if (aulaDTO.getProfessorId() != null) {
            Optional<Professor> professor = professorRepository.findById(aulaDTO.getProfessorId());
            aula.setProfessor(professor.orElse(null));
        }
    }
}