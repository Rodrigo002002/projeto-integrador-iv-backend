package com.apiathletevision.apiathletevision.controllers;

import com.apiathletevision.apiathletevision.dtos.PagamentoDTO;
import com.apiathletevision.apiathletevision.services.PagamentoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagamento")
@RequiredArgsConstructor
@Tag(name = "Pagamento")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    @GetMapping
    public ResponseEntity<List<PagamentoDTO>> getAllPagamentos() {
        List<PagamentoDTO> pagamentos = pagamentoService.getAllPagamentos();
        return new ResponseEntity<>(pagamentos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoDTO> getPagamentoById(@PathVariable("id") Integer id) {
        return pagamentoService.getPagamentoById(id)
                .map(pagamento -> new ResponseEntity<>(pagamento, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<PagamentoDTO> createPagamento(@RequestBody PagamentoDTO pagamentoDTO) {
        PagamentoDTO pagamento = pagamentoService.createPagamento(pagamentoDTO);
        return new ResponseEntity<>(pagamento, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagamentoDTO> updatePagamento(@PathVariable("id") Integer id, @RequestBody PagamentoDTO pagamentoDTO) {
        PagamentoDTO pagamento = pagamentoService.updatePagamento(id, pagamentoDTO);

        if (pagamento != null) {
            return new ResponseEntity<>(pagamento, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePagamento(@PathVariable("id") Integer id) {
        pagamentoService.deletePagamento(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}