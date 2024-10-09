package com.apiathletevision.apiathletevision.controllers;

import com.apiathletevision.apiathletevision.dtos.entities.AulaDTO;
import com.apiathletevision.apiathletevision.services.impl.AulaServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aula")
@RequiredArgsConstructor
@Tag(name = "Aula")
public class AulaController {

    private final AulaServiceImpl aulaServiceImpl;

    @GetMapping
    public ResponseEntity<List<AulaDTO>> getAllAulas() {
        List<AulaDTO> aulas = aulaServiceImpl.getAllAulas();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AulaDTO> getAulaById(@PathVariable("id") Integer id) {
        return aulaServiceImpl.getAulaById(id)
                .map(aula -> new ResponseEntity<>(aula, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public AulaDTO createAula(@RequestBody AulaDTO aulaRequestDTO) {
        return aulaServiceImpl.createAula(aulaRequestDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AulaDTO> updateAula(@PathVariable("id") Integer id, @RequestBody AulaDTO aulaRequestDTO) {
        AulaDTO aula = aulaServiceImpl.updateAula(id, aulaRequestDTO);

        if (aula != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAula(@PathVariable("id") Integer id) {
        aulaServiceImpl.deleteAula(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}