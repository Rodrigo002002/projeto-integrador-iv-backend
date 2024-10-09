package com.apiathletevision.apiathletevision.controllers;

import com.apiathletevision.apiathletevision.dtos.entities.DocumentoDTO;
import com.apiathletevision.apiathletevision.services.impl.DocumentoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/documento")
@Tag(name = "Documento")
public class DocumentoController {

    @Autowired
    private DocumentoService documentoService;

    @GetMapping
    public List<DocumentoDTO> getAllDocumentos() {
        return documentoService.getAllDocumentos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentoDTO> getDocumentoById(@PathVariable("id") Integer id) {
        Optional<DocumentoDTO> documento = documentoService.getDocumentoById(id);
        return documento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public DocumentoDTO createDocumento(@RequestBody DocumentoDTO documentoRequestDTO) {
        return documentoService.createDocumento(documentoRequestDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocumentoDTO> updateDocumento(@PathVariable("id") Integer id, @RequestBody DocumentoDTO documentoRequestDTO) {
        DocumentoDTO documento = documentoService.updateDocumento(id, documentoRequestDTO);

        if (documento != null) {
            return new ResponseEntity<>(documento, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocumento(@PathVariable("id") Integer id) {
        documentoService.deleteDocumento(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}