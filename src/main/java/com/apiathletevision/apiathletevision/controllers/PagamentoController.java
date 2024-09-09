package com.apiathletevision.apiathletevision.controllers;

import com.apiathletevision.apiathletevision.dtos.PagamentoDTO;
import com.apiathletevision.apiathletevision.entities.Pagamento;
import com.apiathletevision.apiathletevision.services.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pagamento")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @GetMapping
    public List<Pagamento> getAllPagamentos() {
        return pagamentoService.getAllPagamentos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pagamento> getPagamentoById(@PathVariable Integer id) {
        Optional<Pagamento> pagamento = pagamentoService.getPagamentoById(id);
        return pagamento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Pagamento createPagamento(@RequestBody PagamentoDTO pagamentoDTO) {
        return pagamentoService.createPagamento(pagamentoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pagamento> updatePagamento(@PathVariable Integer id, @RequestBody PagamentoDTO pagamentoDTO) {
        Pagamento updatedPagamento = pagamentoService.updatePagamento(id, pagamentoDTO);
        return updatedPagamento != null ? ResponseEntity.ok(updatedPagamento) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePagamento(@PathVariable Integer id) {
        pagamentoService.deletePagamento(id);
        return ResponseEntity.noContent().build();
    }
}