package com.apiathletevision.apiathletevision.controllers;

import com.apiathletevision.apiathletevision.dtos.EquipeDTO;
import com.apiathletevision.apiathletevision.services.EquipeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipe")
@RequiredArgsConstructor
public class EquipeController {

    private final EquipeService equipeService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<EquipeDTO>> getAllEquipes() {
        List<EquipeDTO> equipes = equipeService.getAllEquipes();
        return new ResponseEntity<>(equipes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipeDTO> getEquipeById(@PathVariable("id") Integer id) {
        return equipeService.getEquipeById(id)
                .map(equipe -> new ResponseEntity<>(equipe, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<EquipeDTO> createEquipe(@RequestBody EquipeDTO equipeDTO) {
        EquipeDTO equipe = equipeService.createEquipe(equipeDTO);
        return new ResponseEntity<>(equipe, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipeDTO> updateEquipe(@PathVariable("id") Integer id, @RequestBody EquipeDTO equipeDTO) {
        EquipeDTO equipe = equipeService.updateEquipe(id, equipeDTO);

        if (equipe != null) {
            return new ResponseEntity<>(equipe, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipe(@PathVariable("id") Integer id) {
        equipeService.deleteEquipe(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}