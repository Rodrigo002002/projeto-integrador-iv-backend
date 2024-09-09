package com.apiathletevision.apiathletevision.controllers;

import com.apiathletevision.apiathletevision.dtos.EquipeDTO;
import com.apiathletevision.apiathletevision.entities.Equipe;
import com.apiathletevision.apiathletevision.services.EquipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/equipe")
public class EquipeController {

    @Autowired
    private EquipeService equipeService;

    @GetMapping
    public List<Equipe> getAllEquipes() {
        return equipeService.getAllEquipes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipe> getEquipeById(@PathVariable Integer id) {
        Optional<Equipe> equipe = equipeService.getEquipeById(id);
        return equipe.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Equipe createEquipe(@RequestBody EquipeDTO equipeDTO) {
        return equipeService.createEquipe(equipeDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipe> updateEquipe(@PathVariable Integer id, @RequestBody EquipeDTO equipeDTO) {
        Equipe updatedEquipe = equipeService.updateEquipe(id, equipeDTO);
        return updatedEquipe != null ? ResponseEntity.ok(updatedEquipe) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipe(@PathVariable Integer id) {
        equipeService.deleteEquipe(id);
        return ResponseEntity.noContent().build();
    }
}