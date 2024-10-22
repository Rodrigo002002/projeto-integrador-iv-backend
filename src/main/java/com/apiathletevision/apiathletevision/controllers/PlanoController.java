package com.apiathletevision.apiathletevision.controllers;

import com.apiathletevision.apiathletevision.dtos.entities.PlanoDTO;
import com.apiathletevision.apiathletevision.dtos.groups.AppGroup;
import com.apiathletevision.apiathletevision.dtos.response.PageDTO;
import com.apiathletevision.apiathletevision.dtos.select2.Select2OptionsDTO;
import com.apiathletevision.apiathletevision.entities.Plano;
import com.apiathletevision.apiathletevision.services.PlanoService;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Planos")
@RestController
@RequestMapping("${api-prefix}/planos")
@RequiredArgsConstructor
public class PlanoController {

    private final PlanoService planoService;

    @GetMapping
    @Operation(summary = "Listar", description = "Listar")
    @JsonView(AppGroup.ResponsePage.class)
    public ResponseEntity<PageDTO<Plano, PlanoDTO>> findAll(
            @RequestParam(name = "pageNo", defaultValue = "0") int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(name = "search", required = false) String search,
            @RequestParam(name = "status", required = false) Boolean status
    ) {
        return ResponseEntity.ok(planoService.findAll(pageNo, pageSize, search, status));
    }

    @Operation(summary = "Pesquisar", description = "Pesquisar planos para Select2", tags = {"Select2"})
    @GetMapping("/select2")
    public ResponseEntity<List<Select2OptionsDTO>> findAllToSelect2(
            @RequestParam(name = "q", required = false, defaultValue = "") String searchTerm
    ) {
        return ResponseEntity.ok(planoService.findAllToSelect2(searchTerm));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanoDTO> findById(@PathVariable("id") int id) throws BadRequestException {
        return ResponseEntity.ok(planoService.findById(id));
    }

    @Operation(summary = "Cadastrar", description = "Cadastrar")
    @JsonView({AppGroup.Response.class})
    @PostMapping
    public ResponseEntity<PlanoDTO> create(
            @Validated(AppGroup.Request.class)
            @RequestBody
            @JsonView(AppGroup.Request.class)
            PlanoDTO planoDTO
    ) {
        return ResponseEntity.ok(planoService.create(planoDTO));
    }

    @Operation(summary = "Editar", description = "Editar")
    @PutMapping("/{id}")
    public ResponseEntity<PlanoDTO> update(
            @PathVariable("id") int id,
            @Validated(AppGroup.Request.class)
            @RequestBody
            @JsonView(AppGroup.Request.class)
            PlanoDTO planoDTO
    ) throws BadRequestException {
        planoDTO.setId(id);

        return ResponseEntity.ok(planoService.update(planoDTO));
    }

    @Operation(summary = "Deletar", description = "Deletar")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) throws BadRequestException {
        planoService.delete(id);

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Ativar", description = "Ativar plano")
    @PutMapping({"/{id}/ativar"})
    public ResponseEntity<?> enable(@PathVariable("id") int id) {
        this.planoService.changeStatus(id, true);
        return ResponseEntity.ok(null);
    }

    @Operation(summary = "Desativar", description = "Desativar plano")
    @PutMapping({"/{id}/desativar"})
    public ResponseEntity<?> disable(@PathVariable("id") int id) {
        this.planoService.changeStatus(id, false);
        return ResponseEntity.ok(null);
    }
}