package com.apiathletevision.apiathletevision.services;

import com.apiathletevision.apiathletevision.dtos.AulaDTO;
import com.apiathletevision.apiathletevision.entities.Aula;
import com.apiathletevision.apiathletevision.entities.Aluno;
import com.apiathletevision.apiathletevision.entities.Turma;
import com.apiathletevision.apiathletevision.repositories.AulaRepository;
import com.apiathletevision.apiathletevision.repositories.AlunoRepository;
import com.apiathletevision.apiathletevision.repositories.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AulaService {

    @Autowired
    private AulaRepository aulaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    public List<Aula> getAllAulas() {
        return aulaRepository.findAll();
    }

    public Optional<Aula> getAulaById(Integer id) {
        return aulaRepository.findById(id);
    }

    public Aula createAula(AulaDTO aulaDTO) {
        Aula aula = new Aula();
        aula.setData(aulaDTO.getData());
        aula.setTurma(turmaRepository.findById(aulaDTO.getTurmaId()).orElse(null));

        List<Aluno> alunosPresentes = alunoRepository.findAllById(aulaDTO.getAlunosPresentesIds());
        aula.setAlunosPresentes(alunosPresentes);

        return aulaRepository.save(aula);
    }

    public Aula updateAula(Integer id, AulaDTO aulaDTO) {
        Optional<Aula> optionalAula = aulaRepository.findById(id);
        if (optionalAula.isPresent()) {
            Aula aula = optionalAula.get();
            aula.setData(aulaDTO.getData());
            aula.setTurma(turmaRepository.findById(aulaDTO.getTurmaId()).orElse(null));

            List<Aluno> alunosPresentes = alunoRepository.findAllById(aulaDTO.getAlunosPresentesIds());
            aula.setAlunosPresentes(alunosPresentes);

            return aulaRepository.save(aula);
        }
        return null;
    }

    public void deleteAula(Integer id) {
        aulaRepository.deleteById(id);
    }
}