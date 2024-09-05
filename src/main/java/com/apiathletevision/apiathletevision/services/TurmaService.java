package com.apiathletevision.apiathletevision.services;

import com.apiathletevision.apiathletevision.entities.Turma;
import com.apiathletevision.apiathletevision.repositories.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    public List<Turma> findAll() {
        return turmaRepository.findAll();
    }

    public Optional<Turma> findById(Integer id) {
        return turmaRepository.findById(id);
    }

    public Turma save(Turma turma) {
        return turmaRepository.save(turma);
    }

    public void deleteById(Integer id) {
        turmaRepository.deleteById(id);
    }
}