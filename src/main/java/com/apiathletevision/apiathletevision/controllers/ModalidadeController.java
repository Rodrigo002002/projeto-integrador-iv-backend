package com.apiathletevision.apiathletevision.controllers;

import com.apiathletevision.apiathletevision.dtos.ModalidadeDTO;
import com.apiathletevision.apiathletevision.entities.Modalidade;
import com.apiathletevision.apiathletevision.services.ModalidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/modalidade")
public class ModalidadeController {

    @Autowired
    private ModalidadeService modalidadeService;

    @GetMapping
    public List<Modalidade> getAllModalidades() {
        return modalidadeService.getAllModalidades();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Modalidade> getModalidadeById(@PathVariable Integer id) {
        Optional<Modalidade> modalidade = modalidadeService.getModalidadeById(id);
        return modalidade.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Modalidade createModalidade(@RequestBody ModalidadeDTO modalidadeDTO) {
        return modalidadeService.createModalidade(modalidadeDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Modalidade> updateModalidade(@PathVariable Integer id, @RequestBody ModalidadeDTO modalidadeDTO) {
        Modalidade updatedModalidade = modalidadeService.updateModalidade(id, modalidadeDTO);
        return updatedModalidade != null ? ResponseEntity.ok(updatedModalidade) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModalidade(@PathVariable Integer id) {
        modalidadeService.deleteModalidade(id);
        return ResponseEntity.noContent().build();
    }
}