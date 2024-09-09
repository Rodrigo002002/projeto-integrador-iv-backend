package com.apiathletevision.apiathletevision.controllers;

import com.apiathletevision.apiathletevision.dtos.ResponsavelDTO;
import com.apiathletevision.apiathletevision.entities.Responsavel;
import com.apiathletevision.apiathletevision.services.ResponsavelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/responsavel")
public class ResponsavelController {

    @Autowired
    private ResponsavelService responsavelService;

    @PostMapping
    public ResponseEntity<ResponsavelDTO> createResponsavel(@RequestBody ResponsavelDTO responsavelDTO) {
        Responsavel responsavel = responsavelService.createResponsavel(responsavelDTO);
        ResponsavelDTO dto = convertToDTO(responsavel);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponsavelDTO> updateResponsavel(@PathVariable UUID id, @RequestBody ResponsavelDTO responsavelDTO) {
        Responsavel responsavel = responsavelService.updateResponsavel(id, responsavelDTO);
        if (responsavel != null) {
            ResponsavelDTO dto = convertToDTO(responsavel);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResponsavel(@PathVariable UUID id) {
        responsavelService.deleteResponsavel(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsavelDTO> getResponsavelById(@PathVariable UUID id) {
        return responsavelService.getResponsavelById(id)
                .map(responsavel -> new ResponseEntity<>(convertToDTO(responsavel), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<ResponsavelDTO>> getAllResponsaveis() {
        List<Responsavel> responsaveis = responsavelService.getAllResponsaveis();
        List<ResponsavelDTO> dtos = responsaveis.stream()
                .map(this::convertToDTO)
                .toList();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    private ResponsavelDTO convertToDTO(Responsavel responsavel) {
        ResponsavelDTO responsavelDTO = new ResponsavelDTO();
        responsavelDTO.setNome(responsavel.getNome());
        responsavelDTO.setRole(responsavel.getRole());
        responsavelDTO.setTelefone(responsavel.getTelefone());
        responsavelDTO.setEmail(responsavel.getEmail());
        responsavelDTO.setAlunosIds(responsavel.getAlunos().stream().map(a -> a.getId()).toList());
        return responsavelDTO;
    }
}