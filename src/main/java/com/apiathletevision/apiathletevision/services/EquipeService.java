package com.apiathletevision.apiathletevision.services;

import com.apiathletevision.apiathletevision.dtos.EquipeDTO;
import com.apiathletevision.apiathletevision.entities.Aluno;
import com.apiathletevision.apiathletevision.entities.Equipe;
import com.apiathletevision.apiathletevision.repositories.AlunoRepository;
import com.apiathletevision.apiathletevision.repositories.EquipeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EquipeService {

    private final EquipeRepository equipeRepository;
    private final AlunoRepository alunoRepository;
    private final ModelMapper modelMapper;

    public List<EquipeDTO> getAllEquipes() {
        return equipeRepository.findAll().stream().map(equipe -> modelMapper.map(equipe, EquipeDTO.class)).toList();
    }

    public Optional<EquipeDTO> getEquipeById(Integer id) {
        Optional<Equipe> equipe = equipeRepository.findById(id);
        EquipeDTO equipeRequestDTO = modelMapper.map(equipe, EquipeDTO.class);
        return Optional.ofNullable(equipeRequestDTO);
    }

    public EquipeDTO createEquipe(EquipeDTO equipeRequestDTO) {
        Equipe equipe = new Equipe();

        equipe.setNome(equipeRequestDTO.getNome());
        List<Aluno> alunos = alunoRepository.findAllById(equipeRequestDTO.getAlunosIds());
        equipe.setAlunos(alunos);

        equipe = equipeRepository.save(equipe);

        return modelMapper.map(equipe, EquipeDTO.class);
    }

    public EquipeDTO updateEquipe(Integer id, EquipeDTO equipeRequestDTO) {
        Optional<Equipe> optionalEquipe = equipeRepository.findById(id);

        if (optionalEquipe.isPresent()) {
            Equipe equipe = optionalEquipe.get();
            equipe.setId(id);
            equipe.setNome(equipeRequestDTO.getNome());
            List<Aluno> alunos = alunoRepository.findAllById(equipeRequestDTO.getAlunosIds());
            equipe.setAlunos(alunos);

            equipeRepository.save(equipe);

            return modelMapper.map(equipe, EquipeDTO.class);
        }
        return null;
    }

    public void deleteEquipe(Integer id) {
        equipeRepository.deleteById(id);
    }
}