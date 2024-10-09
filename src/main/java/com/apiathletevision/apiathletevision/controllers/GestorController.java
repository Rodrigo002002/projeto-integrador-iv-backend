package com.apiathletevision.apiathletevision.controllers;

import com.apiathletevision.apiathletevision.dtos.entities.GestorDTO;
import com.apiathletevision.apiathletevision.services.impl.GestorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/gestor")
@RequiredArgsConstructor
@Tag(name = "Gestor")
public class GestorController {

    private final GestorService gestorService;

    @PostMapping
    public ResponseEntity<GestorDTO> createGestor(@RequestBody GestorDTO gestorRequestDTO) {
        GestorDTO gestor = gestorService.createGestor(gestorRequestDTO);
        return new ResponseEntity<>(gestor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GestorDTO> updateGestor(@PathVariable("id") UUID id, @RequestBody GestorDTO gestorRequestDTO) {
        GestorDTO gestor = gestorService.updateGestor(id, gestorRequestDTO);

        if (gestor != null) {
            return new ResponseEntity<>(gestor, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGestor(@PathVariable("id") UUID id) {
        gestorService.deleteGestor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GestorDTO> getGestorById(@PathVariable("id") UUID id) {
        return gestorService.getGestorById(id)
                .map(gestor -> new ResponseEntity<>(gestor, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<GestorDTO>> getAllGestores() {
        List<GestorDTO> gestores = gestorService.getAllGestores();
        return new ResponseEntity<>(gestores, HttpStatus.OK);
    }
}