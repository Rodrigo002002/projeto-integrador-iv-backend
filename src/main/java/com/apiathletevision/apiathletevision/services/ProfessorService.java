package com.apiathletevision.apiathletevision.services;

import com.apiathletevision.apiathletevision.dtos.ProfessorDTO;
import com.apiathletevision.apiathletevision.entities.Professor;
import com.apiathletevision.apiathletevision.repositories.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    private final ModelMapper modelMapper;

    public List<ProfessorDTO> getAllProfessors() {
        return professorRepository.findAll().stream().map(professor -> modelMapper.map(professor, ProfessorDTO.class)).toList();
    }

    public Optional<ProfessorDTO> getProfessorById(UUID id) {
        Optional<Professor> professor = professorRepository.findById(id);
        ProfessorDTO professorDTO = modelMapper.map(professor, ProfessorDTO.class);
        return Optional.ofNullable(professorDTO);
    }

    public ProfessorDTO createProfessor(ProfessorDTO professorDTO) {
        Professor professor = new Professor();

        professor.setNome(professorDTO.getNome());
        professor.setRole(professorDTO.getRole());
        professor.setTelefone(professorDTO.getTelefone());
        professor.setEmail(professorDTO.getEmail());

        professor = professorRepository.save(professor);

        return modelMapper.map(professor, ProfessorDTO.class);
    }

    public ProfessorDTO updateProfessor(UUID id, ProfessorDTO professorDTO) {
        Optional<Professor> optionalProfessor = professorRepository.findById(id);

        if (optionalProfessor.isPresent()) {

            Professor professor = optionalProfessor.get();
            professor.setId(id);
            professor.setNome(professorDTO.getNome());
            professor.setRole(professorDTO.getRole());
            professor.setTelefone(professorDTO.getTelefone());
            professor.setEmail(professorDTO.getEmail());

            professor = professorRepository.save(professor);

            return modelMapper.map(professor, ProfessorDTO.class);
        }
        return null;
    }

    public void deleteProfessor(UUID id) {
        professorRepository.deleteById(id);
    }
}