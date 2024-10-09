package com.apiathletevision.apiathletevision.controllers;

import com.apiathletevision.apiathletevision.dtos.entities.EquipeDTO;
import com.apiathletevision.apiathletevision.services.impl.EquipeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipe")
@RequiredArgsConstructor
@Tag(name = "Equipe")
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
    public ResponseEntity<EquipeDTO> createEquipe(@RequestBody EquipeDTO equipeRequestDTO) {
        EquipeDTO equipe = equipeService.createEquipe(equipeRequestDTO);
        return new ResponseEntity<>(equipe, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipeDTO> updateEquipe(@PathVariable("id") Integer id, @RequestBody EquipeDTO equipeRequestDTO) {
        EquipeDTO equipe = equipeService.updateEquipe(id, equipeRequestDTO);

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