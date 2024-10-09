package com.apiathletevision.apiathletevision.controllers;

import com.apiathletevision.apiathletevision.dtos.entities.ResponsavelDTO;
import com.apiathletevision.apiathletevision.services.impl.ResponsavelService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/responsavel")
@RequiredArgsConstructor
@Tag(name = "Responsavel")
public class ResponsavelController {

    private final ResponsavelService responsavelService;

    @PostMapping
    public ResponseEntity<ResponsavelDTO> createResponsavel(@RequestBody ResponsavelDTO responsavelRequestDTO) {
        ResponsavelDTO responsavel = responsavelService.createResponsavel(responsavelRequestDTO);

        return new ResponseEntity<>(responsavel, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponsavelDTO> updateResponsavel(@PathVariable("id") UUID id, @RequestBody ResponsavelDTO responsavelRequestDTO) {
        ResponsavelDTO responsavel = responsavelService.updateResponsavel(id, responsavelRequestDTO);

        if (responsavel != null) {
            return new ResponseEntity<>(responsavel, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResponsavel(@PathVariable("id") UUID id) {
        responsavelService.deleteResponsavel(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsavelDTO> getResponsavelById(@PathVariable("id") UUID id) {
        return responsavelService.getResponsavelById(id)
                .map(responsavel -> new ResponseEntity<>(responsavel, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<ResponsavelDTO>> getAllResponsaveis() {
        List<ResponsavelDTO> responsaveis = responsavelService.getAllResponsaveis();
        return new ResponseEntity<>(responsaveis, HttpStatus.OK);
    }
}