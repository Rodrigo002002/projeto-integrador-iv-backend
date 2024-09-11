package com.apiathletevision.apiathletevision.services;

import com.apiathletevision.apiathletevision.dtos.TurmaDTO;
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
        TurmaDTO turmaDTO = modelMapper.map(turma, TurmaDTO.class);
        return Optional.ofNullable(turmaDTO);
    }

    public TurmaDTO createTurma(TurmaDTO turmaDTO) {
        Turma turma = new Turma();

        List<Modalidade> modalidades = modalidadeRepository.findAllById(turmaDTO.getModalidadeIds());
        turma.setModalidades(modalidades);
        List<Aula> aulas = aulaRepository.findAllById(turmaDTO.getAulaIds());
        turma.setAulas(aulas);
        List<Aluno> alunos = alunoRepository.findAllById(turmaDTO.getAlunoIds());
        turma.setAlunos(alunos);
        Professor professor = professorRepository.findById(turmaDTO.getProfessorId()).orElse(null);
        turma.setProfessor(professor);
        turma.setHorario(turmaDTO.getHorario());

        turma = turmaRepository.save(turma);

        return modelMapper.map(turma, TurmaDTO.class);
    }

    public TurmaDTO updateTurma(Integer id, TurmaDTO turmaDTO) {
        Optional<Turma> optionalTurma = turmaRepository.findById(id);

        if (optionalTurma.isPresent()) {

            Turma turma = optionalTurma.get();
            turma.setId(id);
            List<Modalidade> modalidades = modalidadeRepository.findAllById(turmaDTO.getModalidadeIds());
            turma.setModalidades(modalidades);
            List<Aula> aulas = aulaRepository.findAllById(turmaDTO.getAulaIds());
            turma.setAulas(aulas);
            List<Aluno> alunos = alunoRepository.findAllById(turmaDTO.getAlunoIds());
            turma.setAlunos(alunos);
            Professor professor = professorRepository.findById(turmaDTO.getProfessorId()).orElse(null);
            turma.setProfessor(professor);
            turma.setHorario(turmaDTO.getHorario());

            turma = turmaRepository.save(turma);

            return modelMapper.map(turma, TurmaDTO.class);
        }
        return null;
    }

    public void deleteTurma(Integer id) {
        turmaRepository.deleteById(id);
    }
}