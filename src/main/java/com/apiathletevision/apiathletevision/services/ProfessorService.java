package com.apiathletevision.apiathletevision.services;

import com.apiathletevision.apiathletevision.dtos.ProfessorDTO;
import com.apiathletevision.apiathletevision.entities.Professor;
import com.apiathletevision.apiathletevision.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public List<Professor> getAllProfessors() {
        return professorRepository.findAll();
    }

    public Optional<Professor> getProfessorById(UUID id) {
        return professorRepository.findById(id);
    }

    public Professor createProfessor(ProfessorDTO professorDTO) {
        Professor professor = new Professor();
        professor.setNome(professorDTO.getNome());
        professor.setRole(professorDTO.getRole());
        professor.setTelefone(professorDTO.getTelefone());
        professor.setEmail(professorDTO.getEmail());
        return professorRepository.save(professor);
    }

    public Professor updateProfessor(UUID id, ProfessorDTO professorDTO) {
        Optional<Professor> optionalProfessor = professorRepository.findById(id);
        if (optionalProfessor.isPresent()) {
            Professor professor = optionalProfessor.get();
            professor.setNome(professorDTO.getNome());
            professor.setRole(professorDTO.getRole());
            professor.setTelefone(professorDTO.getTelefone());
            professor.setEmail(professorDTO.getEmail());
            return professorRepository.save(professor);
        }
        return null;
    }

    public void deleteProfessor(UUID id) {
        professorRepository.deleteById(id);
    }
}