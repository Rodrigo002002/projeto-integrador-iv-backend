package com.apiathletevision.apiathletevision.services;

import com.apiathletevision.apiathletevision.dtos.professor.request.ProfessorRequestDTO;
import com.apiathletevision.apiathletevision.dtos.professor.response.ProfessorResponseDTO;
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

    public List<ProfessorResponseDTO> getAllProfessors() {
        return professorRepository.findAll().stream().map(professor -> modelMapper.map(professor, ProfessorResponseDTO.class)).toList();
    }

    public Optional<ProfessorResponseDTO> getProfessorById(UUID id) {
        Optional<Professor> professor = professorRepository.findById(id);
        ProfessorResponseDTO professorResponseDTO = modelMapper.map(professor, ProfessorResponseDTO.class);
        return Optional.ofNullable(professorResponseDTO);
    }

    public ProfessorRequestDTO createProfessor(ProfessorRequestDTO professorRequestDTO) {
        Professor professor = new Professor();

        professor.setNome(professorRequestDTO.getNome());
        professor.setRole(professorRequestDTO.getRole());
        professor.setTelefone(professorRequestDTO.getTelefone());
        professor.setEmail(professorRequestDTO.getEmail());
        professor.setRg(professorRequestDTO.getRg());
        professor.setDocumentos(documentoRepository.findAllById(professorRequestDTO.getDocumentosIds()));
        professor.setCpf(professorRequestDTO.getCpf());

        professor = professorRepository.save(professor);

        return modelMapper.map(professor, ProfessorRequestDTO.class);
    }

    public ProfessorRequestDTO updateProfessor(UUID id, ProfessorRequestDTO professorRequestDTO) {
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

            return modelMapper.map(professor, ProfessorRequestDTO.class);
        }
        return null;
    }

    public void deleteProfessor(UUID id) {
        professorRepository.deleteById(id);
    }
}