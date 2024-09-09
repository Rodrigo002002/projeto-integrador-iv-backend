package com.apiathletevision.apiathletevision.controllers;

import com.apiathletevision.apiathletevision.dtos.ProfessorDTO;
import com.apiathletevision.apiathletevision.entities.Professor;
import com.apiathletevision.apiathletevision.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @GetMapping
    public ResponseEntity<List<Professor>> getAllProfessors() {
        List<Professor> professors = professorService.getAllProfessors();
        return ResponseEntity.ok(professors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> getProfessorById(@PathVariable UUID id) {
        Optional<Professor> professor = professorService.getProfessorById(id);
        return professor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Professor> createProfessor(@RequestBody ProfessorDTO professorDTO) {
        Professor createdProfessor = professorService.createProfessor(professorDTO);
        return ResponseEntity.ok(createdProfessor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor> updateProfessor(@PathVariable UUID id, @RequestBody ProfessorDTO professorDTO) {
        Professor updatedProfessor = professorService.updateProfessor(id, professorDTO);
        return updatedProfessor != null ? ResponseEntity.ok(updatedProfessor) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfessor(@PathVariable UUID id) {
        professorService.deleteProfessor(id);
        return ResponseEntity.noContent().build();
    }
}