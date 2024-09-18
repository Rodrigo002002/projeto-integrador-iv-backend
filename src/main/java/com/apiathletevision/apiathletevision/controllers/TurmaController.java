package com.apiathletevision.apiathletevision.controllers;

import com.apiathletevision.apiathletevision.dtos.TurmaDTO;
import com.apiathletevision.apiathletevision.services.TurmaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turma")
@RequiredArgsConstructor
@Tag(name = "Turma")
public class TurmaController {

    private final TurmaService turmaService;

    @GetMapping
    public ResponseEntity<List<TurmaDTO>> getAllTurmas() {
        List<TurmaDTO> turmas = turmaService.getAllTurmas();
        return new ResponseEntity<>(turmas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurmaDTO> getTurmaById(@PathVariable("id") Integer id) {
        return turmaService.getTurmaById(id)
                .map(turma -> new ResponseEntity<>(turma, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<TurmaDTO> createTurma(@RequestBody TurmaDTO turmaDTO) {
        TurmaDTO turma = turmaService.createTurma(turmaDTO);
        return new ResponseEntity<>(turma, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TurmaDTO> updateTurma(@PathVariable("id") Integer id, @RequestBody TurmaDTO turmaDTO) {
        TurmaDTO turma = turmaService.updateTurma(id, turmaDTO);

        if (turma != null) {
            return new ResponseEntity<>(turma, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTurma(@PathVariable("id") Integer id) {
        turmaService.deleteTurma(id);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}