package com.apiathletevision.apiathletevision.controllers;

import com.apiathletevision.apiathletevision.dtos.PlanoDTO;
import com.apiathletevision.apiathletevision.services.PlanoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plano")
@RequiredArgsConstructor
public class PlanoController {

    private final PlanoService planoService;

    @GetMapping
    public ResponseEntity<List<PlanoDTO>> getAllPlanos() {
        List<PlanoDTO> planos = planoService.getAllPlanos();
        return new ResponseEntity<>(planos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanoDTO> getPlanoById(@PathVariable("id") Integer id) {
        return planoService.getPlanoById(id)
                .map(plano -> new ResponseEntity<>(plano, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<PlanoDTO> createPlano(@RequestBody PlanoDTO planoDTO) {
        PlanoDTO plano = planoService.createPlano(planoDTO);
        return new ResponseEntity<>(plano, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanoDTO> updatePlano(@PathVariable("id") Integer id, @RequestBody PlanoDTO planoDTO) {
        PlanoDTO plano = planoService.updatePlano(id, planoDTO);

        if (plano != null) {
            return new ResponseEntity<>(plano, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlano(@PathVariable("id") Integer id) {
        planoService.deletePlano(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}