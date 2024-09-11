package com.apiathletevision.apiathletevision.controllers;

import com.apiathletevision.apiathletevision.dtos.ModalidadeDTO;
import com.apiathletevision.apiathletevision.services.ModalidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/modalidade")
@RequiredArgsConstructor
public class ModalidadeController {

    private final ModalidadeService modalidadeService;

    @GetMapping
    public ResponseEntity<List<ModalidadeDTO>> getAllModalidades() {
        List<ModalidadeDTO> modalidades = modalidadeService.getAllModalidades();
        return new ResponseEntity<>(modalidades, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModalidadeDTO> getModalidadeById(@PathVariable("id") Integer id) {
        return modalidadeService.getModalidadeById(id)
                .map(modalidade -> new ResponseEntity<>(modalidade, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ModalidadeDTO> createModalidade(@RequestBody ModalidadeDTO modalidadeDTO) {
        ModalidadeDTO modalidade = modalidadeService.createModalidade(modalidadeDTO);
        return new ResponseEntity<>(modalidade, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModalidadeDTO> updateModalidade(@PathVariable("id") Integer id, @RequestBody ModalidadeDTO modalidadeDTO) {
        ModalidadeDTO modalidade = modalidadeService.updateModalidade(id, modalidadeDTO);
        return new ResponseEntity<>(modalidade, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModalidade(@PathVariable("id") Integer id) {
        modalidadeService.deleteModalidade(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}