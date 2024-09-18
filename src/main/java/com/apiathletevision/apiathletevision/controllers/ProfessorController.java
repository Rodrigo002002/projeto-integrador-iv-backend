package com.apiathletevision.apiathletevision.controllers;

import com.apiathletevision.apiathletevision.dtos.professor.request.ProfessorRequestDTO;
import com.apiathletevision.apiathletevision.dtos.professor.response.ProfessorResponseDTO;
import com.apiathletevision.apiathletevision.services.ProfessorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/professor")
@RequiredArgsConstructor
@Tag(name = "Professor")
public class ProfessorController {

    private final ProfessorService professorService;

    @GetMapping
    public ResponseEntity<List<ProfessorResponseDTO>> getAllProfessors() {
        List<ProfessorResponseDTO> professores = professorService.getAllProfessors();
        return new ResponseEntity<>(professores, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorResponseDTO> getProfessorById(@PathVariable("id") UUID id) {
        return professorService.getProfessorById(id)
                .map(professor -> new ResponseEntity<>(professor, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ProfessorRequestDTO> createProfessor(@RequestBody ProfessorRequestDTO professorRequestDTO) {
        ProfessorRequestDTO professor = professorService.createProfessor(professorRequestDTO);
        return new ResponseEntity<>(professor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessorRequestDTO> updateProfessor(@PathVariable("id") UUID id, @RequestBody ProfessorRequestDTO professorRequestDTO) {
        ProfessorRequestDTO professor = professorService.updateProfessor(id, professorRequestDTO);

        if (professor != null) {
            return new ResponseEntity<>(professor, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfessor(@PathVariable("id") UUID id) {
        professorService.deleteProfessor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}