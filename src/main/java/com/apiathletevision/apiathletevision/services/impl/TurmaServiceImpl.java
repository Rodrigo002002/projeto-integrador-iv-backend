package com.apiathletevision.apiathletevision.services.impl;

import com.apiathletevision.apiathletevision.dtos.entities.AlunoDTO;
import com.apiathletevision.apiathletevision.dtos.entities.AulaDTO;
import com.apiathletevision.apiathletevision.dtos.entities.TurmaDTO;
import com.apiathletevision.apiathletevision.dtos.response.PageDTO;
import com.apiathletevision.apiathletevision.entities.*;
import com.apiathletevision.apiathletevision.exeptions.BadRequestException;
import com.apiathletevision.apiathletevision.mappers.AulaMapper;
import com.apiathletevision.apiathletevision.mappers.TurmaMapper;
import com.apiathletevision.apiathletevision.repositories.*;
import com.apiathletevision.apiathletevision.services.TurmaService;
import com.apiathletevision.apiathletevision.services.specifications.TurmaSpecification;
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
public class TurmaServiceImpl implements TurmaService {

    private final TurmaRepository turmaRepository;
    private final TurmaMapper turmaMapper;
    private final AlunoRepository alunoRepository;
    private final AulaRepository aulaRepository;
    private final AulaMapper aulaMapper;
    private final ModalidadeRepository modalidadeRepository;
    private final ProfessorRepository professorRepository;

    public PageDTO<Turma, TurmaDTO> findAll(int pageNo, int pageSize, String search) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("data"));
        Specification<Turma> specification = TurmaSpecification.getFilters(search);
        Page<Turma> aulaPage = turmaRepository.findAll(specification, pageable);
        return new PageDTO<>(aulaPage, turmaMapper::toDto);
    }

    @Override
    public TurmaDTO findById(int id) throws BadRequestException {
        Turma turma = getTurma(id);
        return turmaMapper.toDto(turma);
    }

    @Override
    @Transactional
    public TurmaDTO create(TurmaDTO turmaDTO) {
        Turma turma = turmaMapper.toEntity(turmaDTO);
        setAssociations(turmaDTO, turma);
        turma = turmaRepository.save(turma);
        return turmaMapper.toDto(turma);
    }

    @Override
    @Transactional
    public TurmaDTO update(TurmaDTO turmaDTO) throws BadRequestException {
        Turma turma = getTurma(turmaDTO.getId());
        turmaMapper.partialUpdate(turmaDTO, turma);

        setAssociations(turmaDTO, turma);

        turma = turmaRepository.save(turma);
        return turmaMapper.toDto(turma);
    }

    @Override
    public void delete(int id) throws BadRequestException {
        Turma turma = getTurma(id);
        turmaRepository.delete(turma);
    }

    private Turma getTurma(int id) throws BadRequestException {
        return turmaRepository.findById(id).orElseThrow(() ->
                new BadRequestException("Turma com o ID: " + id + " não encontrada"));
    }

    @Override
    public List<AulaDTO> findAllAulaByTurmaId(int id) {
        List<Aula> aulas = aulaRepository.findAllByTurma_id(id);
        if (aulas.isEmpty()) {
            throw new BadRequestException("Nenhuma aula encontrada");
        }
        return aulaMapper.toDtoList(aulas);
    }

    private void setAssociations(TurmaDTO turmaDTO, Turma turma) {
        if (turmaDTO.getModalidadeId() != null) {
            Optional<Modalidade> modalidade = modalidadeRepository.findById(turmaDTO.getModalidadeId());
            turma.setModalidade(modalidade.orElse(null));
        }

        if (turmaDTO.getAlunosIds() != null && !turmaDTO.getAlunosIds().isEmpty()) {
            List<Aluno> alunos = alunoRepository.findAllById(turmaDTO.getAlunosIds());
            turma.setAlunos(alunos);
        }

        if (turmaDTO.getProfessorId() != null) {
            Optional<Professor> professor = professorRepository.findById(turmaDTO.getProfessorId());
            turma.setProfessor(professor.orElse(null));
        }
    }
}