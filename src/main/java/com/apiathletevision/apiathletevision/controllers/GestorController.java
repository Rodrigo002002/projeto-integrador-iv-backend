package com.apiathletevision.apiathletevision.controllers;

import com.apiathletevision.apiathletevision.dtos.GestorDTO;
import com.apiathletevision.apiathletevision.entities.Gestor;
import com.apiathletevision.apiathletevision.services.GestorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/gestor")
public class GestorController {

    @Autowired
    private GestorService gestorService;

    @PostMapping
    public ResponseEntity<GestorDTO> createGestor(@RequestBody GestorDTO gestorDTO) {
        Gestor gestor = gestorService.createGestor(gestorDTO);
        GestorDTO dto = convertToDTO(gestor);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GestorDTO> updateGestor(@PathVariable UUID id, @RequestBody GestorDTO gestorDTO) {
        Gestor gestor = gestorService.updateGestor(id, gestorDTO);
        if (gestor != null) {
            GestorDTO dto = convertToDTO(gestor);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGestor(@PathVariable UUID id) {
        gestorService.deleteGestor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GestorDTO> getGestorById(@PathVariable UUID id) {
        return gestorService.getGestorById(id)
                .map(gestor -> new ResponseEntity<>(convertToDTO(gestor), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<GestorDTO>> getAllGestores() {
        List<Gestor> gestores = gestorService.getAllGestores();
        List<GestorDTO> dtos = gestores.stream()
                .map(this::convertToDTO)
                .toList();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    private GestorDTO convertToDTO(Gestor gestor) {
        GestorDTO gestorDTO = new GestorDTO();
        gestorDTO.setNome(gestor.getNome());
        gestorDTO.setRole(gestor.getRole());
        gestorDTO.setTelefone(gestor.getTelefone());
        gestorDTO.setEmail(gestor.getEmail());
        return gestorDTO;
    }
}