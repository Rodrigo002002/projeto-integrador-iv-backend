package com.apiathletevision.apiathletevision.controllers;

import com.apiathletevision.apiathletevision.dtos.AulaDTO;
import com.apiathletevision.apiathletevision.services.AulaService;
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

    private final AulaService aulaService;

    @GetMapping
    public ResponseEntity<List<AulaDTO>> getAllAulas() {
        List<AulaDTO> aulas = aulaService.getAllAulas();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AulaDTO> getAulaById(@PathVariable("id") Integer id) {
        return aulaService.getAulaById(id)
                .map(aula -> new ResponseEntity<>(aula, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public AulaDTO createAula(@RequestBody AulaDTO aulaDTO) {
        return aulaService.createAula(aulaDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AulaDTO> updateAula(@PathVariable("id") Integer id, @RequestBody AulaDTO aulaDTO) {
        AulaDTO aula = aulaService.updateAula(id, aulaDTO);

        if (aula != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAula(@PathVariable("id") Integer id) {
        aulaService.deleteAula(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}