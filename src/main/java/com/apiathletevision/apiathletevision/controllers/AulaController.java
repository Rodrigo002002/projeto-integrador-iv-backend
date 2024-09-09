package com.apiathletevision.apiathletevision.controllers;

import com.apiathletevision.apiathletevision.dtos.AulaDTO;
import com.apiathletevision.apiathletevision.entities.Aula;
import com.apiathletevision.apiathletevision.services.AulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/aula")
public class AulaController {

    @Autowired
    private AulaService aulaService;

    @GetMapping
    public List<Aula> getAllAulas() {
        return aulaService.getAllAulas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aula> getAulaById(@PathVariable Integer id) {
        Optional<Aula> aula = aulaService.getAulaById(id);
        return aula.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Aula createAula(@RequestBody AulaDTO aulaDTO) {
        return aulaService.createAula(aulaDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aula> updateAula(@PathVariable Integer id, @RequestBody AulaDTO aulaDTO) {
        Aula updatedAula = aulaService.updateAula(id, aulaDTO);
        return updatedAula != null ? ResponseEntity.ok(updatedAula) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAula(@PathVariable Integer id) {
        aulaService.deleteAula(id);
        return ResponseEntity.noContent().build();
    }
}