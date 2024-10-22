package com.apiathletevision.apiathletevision.controllers;

import com.apiathletevision.apiathletevision.dtos.entities.PagamentoDTO;
import com.apiathletevision.apiathletevision.dtos.groups.AppGroup;
import com.apiathletevision.apiathletevision.dtos.response.PageDTO;
import com.apiathletevision.apiathletevision.entities.Pagamento;
import com.apiathletevision.apiathletevision.services.PagamentoService;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Pagamentos")
@RestController
@RequestMapping("${api-prefix}/pagamentos")
@RequiredArgsConstructor
public class PagamentoController {

    private final PagamentoService pagamentoService;

    @GetMapping
    @Operation(summary = "Listar", description = "Listar")
    @JsonView(AppGroup.ResponsePage.class)
    public ResponseEntity<PageDTO<Pagamento, PagamentoDTO>> findAll(
            @RequestParam(name = "pageNo", defaultValue = "0") int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(name = "search", required = false) String search,
            @RequestParam(name = "status", required = false) Boolean status
    ) {
        return ResponseEntity.ok(pagamentoService.findAll(pageNo, pageSize, search, status));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoDTO> findById(@PathVariable("id") int id) throws BadRequestException {
        return ResponseEntity.ok(pagamentoService.findById(id));
    }

    @Operation(summary = "Cadastrar", description = "Cadastrar")
    @JsonView({AppGroup.Response.class})
    @PostMapping
    public ResponseEntity<PagamentoDTO> create(
            @Validated(AppGroup.Request.class)
            @RequestBody
            @JsonView(AppGroup.Request.class)
            PagamentoDTO pagamentoDTO
    ) {
        return ResponseEntity.ok(pagamentoService.create(pagamentoDTO));
    }

    @Operation(summary = "Editar", description = "Editar")
    @PutMapping("/{id}")
    public ResponseEntity<PagamentoDTO> update(
            @PathVariable("id") int id,
            @Validated(AppGroup.Request.class)
            @RequestBody
            @JsonView(AppGroup.Request.class)
            PagamentoDTO pagamentoDTO
    ) throws BadRequestException {
        pagamentoDTO.setId(id);

        return ResponseEntity.ok(pagamentoService.update(pagamentoDTO));
    }

    @Operation(summary = "Deletar", description = "Deletar")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) throws BadRequestException {
        pagamentoService.delete(id);

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Ativar", description = "Ativar pagamento")
    @PutMapping({"/{id}/ativar"})
    public ResponseEntity<?> enable(@PathVariable("id") int id) {
        this.pagamentoService.changeStatus(id, true);
        return ResponseEntity.ok(null);
    }

    @Operation(summary = "Desativar", description = "Desativar pagamento")
    @PutMapping({"/{id}/desativar"})
    public ResponseEntity<?> disable(@PathVariable("id") int id) {
        this.pagamentoService.changeStatus(id, false);
        return ResponseEntity.ok(null);
    }
}