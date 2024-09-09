package com.apiathletevision.apiathletevision.controllers;

import com.apiathletevision.apiathletevision.dtos.TurmaDTO;
import com.apiathletevision.apiathletevision.entities.Turma;
import com.apiathletevision.apiathletevision.services.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/turma")
public class TurmaController {

    @Autowired
    private TurmaService turmaService;

    @GetMapping
    public List<Turma> getAllTurmas() {
        return turmaService.getAllTurmas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turma> getTurmaById(@PathVariable Integer id) {
        Optional<Turma> turma = turmaService.getTurmaById(id);
        return turma.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Turma createTurma(@RequestBody TurmaDTO turmaDTO) {
        return turmaService.createTurma(turmaDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turma> updateTurma(@PathVariable Integer id, @RequestBody TurmaDTO turmaDTO) {
        Turma updatedTurma = turmaService.updateTurma(id, turmaDTO);
        return updatedTurma != null ? ResponseEntity.ok(updatedTurma) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTurma(@PathVariable Integer id) {
        turmaService.deleteTurma(id);
        return ResponseEntity.noContent().build();
    }
}