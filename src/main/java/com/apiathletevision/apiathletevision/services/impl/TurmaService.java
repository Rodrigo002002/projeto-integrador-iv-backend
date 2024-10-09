package com.apiathletevision.apiathletevision.services.impl;

import com.apiathletevision.apiathletevision.dtos.entities.TurmaDTO;
import com.apiathletevision.apiathletevision.entities.*;
import com.apiathletevision.apiathletevision.repositories.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TurmaService {

    private final TurmaRepository turmaRepository;

    private final AlunoRepository alunoRepository;

    private final AulaRepository aulaRepository;

    private final ModalidadeRepository modalidadeRepository;

    private final ProfessorRepository professorRepository;

    private final ModelMapper modelMapper;

    public List<TurmaDTO> getAllTurmas() {
        return turmaRepository.findAll().stream().map(turma -> modelMapper.map(turma, TurmaDTO.class)).toList();
    }

    public Optional<TurmaDTO> getTurmaById(Integer id) {
        Optional<Turma> turma = turmaRepository.findById(id);
        TurmaDTO turmaRequestDTO = modelMapper.map(turma, TurmaDTO.class);
        return Optional.ofNullable(turmaRequestDTO);
    }

    public TurmaDTO createTurma(TurmaDTO turmaRequestDTO) {
        Turma turma = new Turma();

        Optional<Modalidade> modalidade = modalidadeRepository.findById(turmaRequestDTO.getModalidadeId());
        turma.setModalidade(modalidade.orElse(null));

        List<Aula> aulas = aulaRepository.findAllById(turmaRequestDTO.getAulaIds());
        turma.setAulas(aulas);
        List<Aluno> alunos = alunoRepository.findAllById(turmaRequestDTO.getAlunoIds());
        turma.setAlunos(alunos);
        Professor professor = professorRepository.findById(turmaRequestDTO.getProfessorId()).orElse(null);
        turma.setProfessor(professor);
        turma.setPeriodo(turmaRequestDTO.getPeriodo());

        turma = turmaRepository.save(turma);

        return modelMapper.map(turma, TurmaDTO.class);
    }

    public TurmaDTO updateTurma(Integer id, TurmaDTO turmaRequestDTO) {
        Optional<Turma> optionalTurma = turmaRepository.findById(id);

        if (optionalTurma.isPresent()) {

            Turma turma = optionalTurma.get();
            turma.setId(id);

            Optional<Modalidade> modalidade = modalidadeRepository.findById(turmaRequestDTO.getModalidadeId());
            turma.setModalidade(modalidade.orElse(null));

            List<Aula> aulas = aulaRepository.findAllById(turmaRequestDTO.getAulaIds());
            turma.setAulas(aulas);
            List<Aluno> alunos = alunoRepository.findAllById(turmaRequestDTO.getAlunoIds());
            turma.setAlunos(alunos);
            Professor professor = professorRepository.findById(turmaRequestDTO.getProfessorId()).orElse(null);
            turma.setProfessor(professor);
            turma.setPeriodo(turmaRequestDTO.getPeriodo());

            turma = turmaRepository.save(turma);

            return modelMapper.map(turma, TurmaDTO.class);
        }
        return null;
    }

    public void deleteTurma(Integer id) {
        turmaRepository.deleteById(id);
    }
}