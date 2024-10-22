package com.apiathletevision.apiathletevision.controllers;

import com.apiathletevision.apiathletevision.dtos.entities.EquipeDTO;
import com.apiathletevision.apiathletevision.dtos.groups.AppGroup;
import com.apiathletevision.apiathletevision.dtos.response.PageDTO;
import com.apiathletevision.apiathletevision.dtos.select2.Select2OptionsDTO;
import com.apiathletevision.apiathletevision.entities.Equipe;
import com.apiathletevision.apiathletevision.services.EquipeService;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Equipes")
@RestController
@RequestMapping("${api-prefix}/equipes")
@RequiredArgsConstructor
public class EquipeController {

    private final EquipeService equipeService;

    @GetMapping
    @Operation(summary = "Listar", description = "Listar")
    @JsonView(AppGroup.ResponsePage.class)
    public ResponseEntity<PageDTO<Equipe, EquipeDTO>> findAll(
            @RequestParam(name = "pageNo", defaultValue = "0") int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(name = "search", required = false) String search,
            @RequestParam(name = "status", required = false) Boolean status
    ) {
        return ResponseEntity.ok(equipeService.findAll(pageNo, pageSize, search, status));
    }

    @Operation(summary = "Pesquisar", description = "Pesquisar equipes para Select2", tags = {"Select2"})
    @GetMapping("/select2")
    public ResponseEntity<List<Select2OptionsDTO>> findAllToSelect2(
            @RequestParam(name = "q", required = false, defaultValue = "") String searchTerm
    ) {
        return ResponseEntity.ok(equipeService.findAllToSelect2(searchTerm));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipeDTO> findById(@PathVariable("id") int id) throws BadRequestException {
        return ResponseEntity.ok(equipeService.findById(id));
    }

    @Operation(summary = "Cadastrar", description = "Cadastrar")
    @JsonView({AppGroup.Response.class})
    @PostMapping
    public ResponseEntity<EquipeDTO> create(
            @Validated(AppGroup.Request.class)
            @RequestBody
            @JsonView(AppGroup.Request.class)
            EquipeDTO equipeDTO
    ) {
        return ResponseEntity.ok(equipeService.create(equipeDTO));
    }

    @Operation(summary = "Editar", description = "Editar")
    @PutMapping("/{id}")
    public ResponseEntity<EquipeDTO> update(
            @PathVariable("id") int id,
            @Validated(AppGroup.Request.class)
            @RequestBody
            @JsonView(AppGroup.Request.class)
            EquipeDTO equipeDTO
    ) throws BadRequestException {
        equipeDTO.setId(id);

        return ResponseEntity.ok(equipeService.update(equipeDTO));
    }

    @Operation(summary = "Deletar", description = "Deletar")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) throws BadRequestException {
        equipeService.delete(id);

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Ativar", description = "Ativar equipe")
    @PutMapping({"/{id}/ativar"})
    public ResponseEntity<?> enable(@PathVariable("id") int id) {
        this.equipeService.changeStatus(id, true);
        return ResponseEntity.ok(null);
    }

    @Operation(summary = "Desativar", description = "Desativar equipe")
    @PutMapping({"/{id}/desativar"})
    public ResponseEntity<?> disable(@PathVariable("id") int id) {
        this.equipeService.changeStatus(id, false);
        return ResponseEntity.ok(null);
    }
}