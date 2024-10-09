package com.apiathletevision.apiathletevision.services.impl;

import com.apiathletevision.apiathletevision.dtos.entities.ProfessorDTO;
import com.apiathletevision.apiathletevision.entities.Professor;
import com.apiathletevision.apiathletevision.repositories.DocumentoRepository;
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

    private final DocumentoRepository documentoRepository;

    public List<ProfessorDTO> getAllProfessors() {
        return professorRepository.findAll().stream().map(professor -> modelMapper.map(professor, ProfessorDTO.class)).toList();
    }

    public Optional<ProfessorDTO> getProfessorById(UUID id) {
        Optional<Professor> professor = professorRepository.findById(id);
        ProfessorDTO professorResponseDTO = modelMapper.map(professor, ProfessorDTO.class);
        return Optional.ofNullable(professorResponseDTO);
    }

    public ProfessorDTO createProfessor(ProfessorDTO professorRequestDTO) {
        Professor professor = new Professor();

        professor.setNome(professorRequestDTO.getNome());
        professor.setRole(professorRequestDTO.getRole());
        professor.setTelefone(professorRequestDTO.getTelefone());
        professor.setEmail(professorRequestDTO.getEmail());
        professor.setRg(professorRequestDTO.getRg());
        professor.setDocumentos(documentoRepository.findAllById(professorRequestDTO.getDocumentosIds()));
        professor.setCpf(professorRequestDTO.getCpf());

        professor = professorRepository.save(professor);

        return modelMapper.map(professor, ProfessorDTO.class);
    }

    public ProfessorDTO updateProfessor(UUID id, ProfessorDTO professorRequestDTO) {
        Optional<Professor> optionalProfessor = professorRepository.findById(id);

        if (optionalProfessor.isPresent()) {

            Professor professor = optionalProfessor.get();
            professor.setId(id);
            professor.setNome(professorRequestDTO.getNome());
            professor.setRole(professorRequestDTO.getRole());
            professor.setTelefone(professorRequestDTO.getTelefone());
            professor.setEmail(professorRequestDTO.getEmail());
            professor.setRg(professorRequestDTO.getRg());
            professor.setDocumentos(documentoRepository.findAllById(professorRequestDTO.getDocumentosIds()));
            professor.setCpf(professorRequestDTO.getCpf());

            professor = professorRepository.save(professor);

            return modelMapper.map(professor, ProfessorDTO.class);
        }
        return null;
    }

    public void deleteProfessor(UUID id) {
        professorRepository.deleteById(id);
    }
}