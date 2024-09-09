package com.apiathletevision.apiathletevision.controllers;

import com.apiathletevision.apiathletevision.dtos.ServicoDTO;
import com.apiathletevision.apiathletevision.entities.Servico;
import com.apiathletevision.apiathletevision.services.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/servico")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    @GetMapping
    public List<Servico> getAllServicos() {
        return servicoService.getAllServicos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servico> getServicoById(@PathVariable Integer id) {
        Optional<Servico> servico = servicoService.getServicoById(id);
        return servico.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Servico createServico(@RequestBody ServicoDTO servicoDTO) {
        return servicoService.createServico(servicoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servico> updateServico(@PathVariable Integer id, @RequestBody ServicoDTO servicoDTO) {
        Servico updatedServico = servicoService.updateServico(id, servicoDTO);
        return updatedServico != null ? ResponseEntity.ok(updatedServico) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServico(@PathVariable Integer id) {
        servicoService.deleteServico(id);
        return ResponseEntity.noContent().build();
    }
}