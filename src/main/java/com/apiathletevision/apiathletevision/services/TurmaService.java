package com.apiathletevision.apiathletevision.services;

import com.apiathletevision.apiathletevision.dtos.TurmaDTO;
import com.apiathletevision.apiathletevision.entities.Turma;
import com.apiathletevision.apiathletevision.entities.Aluno;
import com.apiathletevision.apiathletevision.entities.Aula;
import com.apiathletevision.apiathletevision.entities.Modalidade;
import com.apiathletevision.apiathletevision.entities.Professor;
import com.apiathletevision.apiathletevision.repositories.TurmaRepository;
import com.apiathletevision.apiathletevision.repositories.AlunoRepository;
import com.apiathletevision.apiathletevision.repositories.AulaRepository;
import com.apiathletevision.apiathletevision.repositories.ModalidadeRepository;
import com.apiathletevision.apiathletevision.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private AulaRepository aulaRepository;

    @Autowired
    private ModalidadeRepository modalidadeRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    public List<Turma> getAllTurmas() {
        return turmaRepository.findAll();
    }

    public Optional<Turma> getTurmaById(Integer id) {
        return turmaRepository.findById(id);
    }

    public Turma createTurma(TurmaDTO turmaDTO) {
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

        return turmaRepository.save(turma);
    }

    public Turma updateTurma(Integer id, TurmaDTO turmaDTO) {
        Optional<Turma> optionalTurma = turmaRepository.findById(id);
        if (optionalTurma.isPresent()) {
            Turma turma = optionalTurma.get();

            List<Modalidade> modalidades = modalidadeRepository.findAllById(turmaDTO.getModalidadeIds());
            turma.setModalidades(modalidades);

            List<Aula> aulas = aulaRepository.findAllById(turmaDTO.getAulaIds());
            turma.setAulas(aulas);

            List<Aluno> alunos = alunoRepository.findAllById(turmaDTO.getAlunoIds());
            turma.setAlunos(alunos);

            Professor professor = professorRepository.findById(turmaDTO.getProfessorId()).orElse(null);
            turma.setProfessor(professor);

            turma.setHorario(turmaDTO.getHorario());

            return turmaRepository.save(turma);
        }
        return null;
    }

    public void deleteTurma(Integer id) {
        turmaRepository.deleteById(id);
    }
}