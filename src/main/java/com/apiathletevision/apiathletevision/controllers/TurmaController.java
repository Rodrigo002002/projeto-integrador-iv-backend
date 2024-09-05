package com.apiathletevision.apiathletevision.controllers;

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
        return turmaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turma> getTurmaById(@PathVariable Integer id) {
        Optional<Turma> turma = turmaService.findById(id);
        return turma.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Turma createTurma(@RequestBody Turma turma) {
        return turmaService.save(turma);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turma> updateTurma(@PathVariable Integer id, @RequestBody Turma turmaDetails) {
        Optional<Turma> turma = turmaService.findById(id);
        if (turma.isPresent()) {
            Turma updatedTurma = turma.get();
            updatedTurma.setModalidades(turmaDetails.getModalidades());
            updatedTurma.setAulas(turmaDetails.getAulas());
            updatedTurma.setAlunos(turmaDetails.getAlunos());
            updatedTurma.setProfessor(turmaDetails.getProfessor());
            updatedTurma.setHorario(turmaDetails.getHorario());
            turmaService.save(updatedTurma);
            return ResponseEntity.ok(updatedTurma);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTurma(@PathVariable Integer id) {
        if (turmaService.findById(id).isPresent()) {
            turmaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}