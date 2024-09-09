package com.apiathletevision.apiathletevision.services;

import com.apiathletevision.apiathletevision.dtos.EquipeDTO;
import com.apiathletevision.apiathletevision.entities.Equipe;
import com.apiathletevision.apiathletevision.entities.Aluno;
import com.apiathletevision.apiathletevision.repositories.EquipeRepository;
import com.apiathletevision.apiathletevision.repositories.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipeService {

    @Autowired
    private EquipeRepository equipeRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    public List<Equipe> getAllEquipes() {
        return equipeRepository.findAll();
    }

    public Optional<Equipe> getEquipeById(Integer id) {
        return equipeRepository.findById(id);
    }

    public Equipe createEquipe(EquipeDTO equipeDTO) {
        Equipe equipe = new Equipe();
        equipe.setNome(equipeDTO.getNome());

        List<Aluno> alunos = alunoRepository.findAllById(equipeDTO.getAlunosIds());
        equipe.setAlunos(alunos);

        return equipeRepository.save(equipe);
    }

    public Equipe updateEquipe(Integer id, EquipeDTO equipeDTO) {
        Optional<Equipe> optionalEquipe = equipeRepository.findById(id);
        if (optionalEquipe.isPresent()) {
            Equipe equipe = optionalEquipe.get();
            equipe.setNome(equipeDTO.getNome());

            List<Aluno> alunos = alunoRepository.findAllById(equipeDTO.getAlunosIds());
            equipe.setAlunos(alunos);

            return equipeRepository.save(equipe);
        }
        return null;
    }

    public void deleteEquipe(Integer id) {
        equipeRepository.deleteById(id);
    }
}