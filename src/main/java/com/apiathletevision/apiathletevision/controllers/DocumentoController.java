package com.apiathletevision.apiathletevision.controllers;

import com.apiathletevision.apiathletevision.dtos.DocumentoDTO;
import com.apiathletevision.apiathletevision.entities.Documento;
import com.apiathletevision.apiathletevision.services.DocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/documento")
public class DocumentoController {

    @Autowired
    private DocumentoService documentoService;

    @GetMapping
    public List<Documento> getAllDocumentos() {
        return documentoService.getAllDocumentos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Documento> getDocumentoById(@PathVariable Integer id) {
        Optional<Documento> documento = documentoService.getDocumentoById(id);
        return documento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Documento createDocumento(@RequestBody DocumentoDTO documentoDTO) {
        return documentoService.createDocumento(documentoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Documento> updateDocumento(@PathVariable Integer id, @RequestBody DocumentoDTO documentoDTO) {
        Documento updatedDocumento = documentoService.updateDocumento(id, documentoDTO);
        return updatedDocumento != null ? ResponseEntity.ok(updatedDocumento) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocumento(@PathVariable Integer id) {
        documentoService.deleteDocumento(id);
        return ResponseEntity.noContent().build();
    }
}