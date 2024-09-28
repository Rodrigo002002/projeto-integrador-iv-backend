package com.apiathletevision.apiathletevision.services;

import com.apiathletevision.apiathletevision.dtos.AulaDTO;
import com.apiathletevision.apiathletevision.entities.Aluno;
import com.apiathletevision.apiathletevision.entities.Aula;
import com.apiathletevision.apiathletevision.repositories.AlunoRepository;
import com.apiathletevision.apiathletevision.repositories.AulaRepository;
import com.apiathletevision.apiathletevision.repositories.TurmaRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AulaService {

    private final AulaRepository aulaRepository;

    private final AlunoRepository alunoRepository;

    private final TurmaRepository turmaRepository;

    private final ModelMapper modelMapper;

    public List<AulaDTO> getAllAulas() {
        return aulaRepository.findAll().stream().map(aula -> modelMapper.map(aula, AulaDTO.class)).toList();
    }

    public Optional<AulaDTO> getAulaById(Integer id) {
        Optional<Aula> aula = aulaRepository.findById(id);
        AulaDTO aulaRequestDTO = modelMapper.map(aula, AulaDTO.class);
        return Optional.ofNullable(aulaRequestDTO);
    }

    public AulaDTO createAula(AulaDTO aulaRequestDTO) {
        Aula aula = new Aula();
        aula.setData(aulaRequestDTO.getData());
        aula.setTurma(turmaRepository.findById(aulaRequestDTO.getTurmaId()).orElse(null));

        List<Aluno> alunosPresentes = alunoRepository.findAllById(aulaRequestDTO.getAlunosPresentesIds());
        aula.setAlunosPresentes(alunosPresentes);
        aulaRepository.save(aula);

        return modelMapper.map(aula, AulaDTO.class);
    }

    public AulaDTO updateAula(Integer id, AulaDTO aulaRequestDTO) {
        Optional<Aula> optionalAula = aulaRepository.findById(id);
        if (optionalAula.isPresent()) {
            Aula aula = optionalAula.get();
            aula.setData(aulaRequestDTO.getData());
            aula.setTurma(turmaRepository.findById(aulaRequestDTO.getTurmaId()).orElse(null));

            List<Aluno> alunosPresentes = alunoRepository.findAllById(aulaRequestDTO.getAlunosPresentesIds());
            aula.setAlunosPresentes(alunosPresentes);
            aulaRepository.save(aula);

            return modelMapper.map(aula, AulaDTO.class);
        }
        return null;
    }

    public void deleteAula(Integer id) {
        aulaRepository.deleteById(id);
    }
}