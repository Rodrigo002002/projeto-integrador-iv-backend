package com.apiathletevision.apiathletevision.controllers;

import com.apiathletevision.apiathletevision.dtos.entities.ModalidadeDTO;
import com.apiathletevision.apiathletevision.dtos.groups.AppGroup;
import com.apiathletevision.apiathletevision.dtos.response.PageDTO;
import com.apiathletevision.apiathletevision.dtos.select2.Select2OptionsDTO;
import com.apiathletevision.apiathletevision.entities.Modalidade;
import com.apiathletevision.apiathletevision.services.ModalidadeService;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Modalidades")
@RestController
@RequestMapping("${api-prefix}/modalidades")
@RequiredArgsConstructor
public class ModalidadeController {

    private final ModalidadeService modalidadeService;

    @GetMapping
    @Operation(summary = "Listar", description = "Listar")
    @JsonView(AppGroup.ResponsePage.class)
    public ResponseEntity<PageDTO<Modalidade, ModalidadeDTO>> findAll(
            @RequestParam(name = "pageNo", defaultValue = "0") int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(name = "search", required = false) String search,
            @RequestParam(name = "status", required = false) Boolean status
    ) {
        return ResponseEntity.ok(modalidadeService.findAll(pageNo, pageSize, search, status));
    }

    @Operation(summary = "Pesquisar", description = "Pesquisar modalidades para Select2", tags = {"Select2"})
    @GetMapping("/select2")
    public ResponseEntity<List<Select2OptionsDTO>> findAllToSelect2(
            @RequestParam(name = "q", required = false, defaultValue = "") String searchTerm
    ) {
        return ResponseEntity.ok(modalidadeService.findAllToSelect2(searchTerm));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModalidadeDTO> findById(@PathVariable("id") int id) throws BadRequestException {
        return ResponseEntity.ok(modalidadeService.findById(id));
    }

    @Operation(summary = "Cadastrar", description = "Cadastrar")
    @JsonView({AppGroup.Response.class})
    @PostMapping
    public ResponseEntity<ModalidadeDTO> create(
            @Validated(AppGroup.Request.class)
            @RequestBody
            @JsonView(AppGroup.Request.class)
            ModalidadeDTO modalidadeDTO
    ) {
        return ResponseEntity.ok(modalidadeService.create(modalidadeDTO));
    }

    @Operation(summary = "Editar", description = "Editar")
    @PutMapping("/{id}")
    public ResponseEntity<ModalidadeDTO> update(
            @PathVariable("id") int id,
            @Validated(AppGroup.Request.class)
            @RequestBody
            @JsonView(AppGroup.Request.class)
            ModalidadeDTO modalidadeDTO
    ) throws BadRequestException {
        modalidadeDTO.setId(id);

        return ResponseEntity.ok(modalidadeService.update(modalidadeDTO));
    }

    @Operation(summary = "Deletar", description = "Deletar")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) throws BadRequestException {
        modalidadeService.delete(id);

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Ativar", description = "Ativar modalidade")
    @PutMapping({"/{id}/ativar"})
    public ResponseEntity<?> enable(@PathVariable("id") int id) {
        this.modalidadeService.changeStatus(id, true);
        return ResponseEntity.ok(null);
    }

    @Operation(summary = "Desativar", description = "Desativar modalidade")
    @PutMapping({"/{id}/desativar"})
    public ResponseEntity<?> disable(@PathVariable("id") int id) {
        this.modalidadeService.changeStatus(id, false);
        return ResponseEntity.ok(null);
    }
}