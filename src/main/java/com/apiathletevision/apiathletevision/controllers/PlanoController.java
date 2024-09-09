package com.apiathletevision.apiathletevision.controllers;

import com.apiathletevision.apiathletevision.dtos.PlanoDTO;
import com.apiathletevision.apiathletevision.entities.Plano;
import com.apiathletevision.apiathletevision.services.PlanoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/plano")
public class PlanoController {

    @Autowired
    private PlanoService planoService;

    @GetMapping
    public List<Plano> getAllPlanos() {
        return planoService.getAllPlanos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plano> getPlanoById(@PathVariable Integer id) {
        Optional<Plano> plano = planoService.getPlanoById(id);
        return plano.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Plano createPlano(@RequestBody PlanoDTO planoDTO) {
        return planoService.createPlano(planoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Plano> updatePlano(@PathVariable Integer id, @RequestBody PlanoDTO planoDTO) {
        Plano updatedPlano = planoService.updatePlano(id, planoDTO);
        return updatedPlano != null ? ResponseEntity.ok(updatedPlano) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlano(@PathVariable Integer id) {
        planoService.deletePlano(id);
        return ResponseEntity.noContent().build();
    }
}