package com.apiathletevision.apiathletevision.controllers;

import com.apiathletevision.apiathletevision.dtos.entities.UsuarioDTO;
import com.apiathletevision.apiathletevision.dtos.groups.AppGroup;
import com.apiathletevision.apiathletevision.dtos.response.PageDTO;
import com.apiathletevision.apiathletevision.dtos.select2.Select2OptionsDTO;
import com.apiathletevision.apiathletevision.entities.Usuario;
import com.apiathletevision.apiathletevision.exeptions.BadRequestException;
import com.apiathletevision.apiathletevision.services.UsuarioService;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Usuário")
@RestController
@RequestMapping("${api-prefix}/gestor/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    @Operation(summary = "Listar", description = "Listar")
    @JsonView(AppGroup.ResponsePage.class)
    public ResponseEntity<PageDTO<Usuario, UsuarioDTO>> findAll(
            @RequestParam(name = "pageNo", defaultValue = "0") int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(name = "search", required = false) String search,
            @RequestParam(name = "status", required = false) Boolean status
    ) {
        return ResponseEntity.ok(usuarioService.findAll(pageNo, pageSize, search, status));
    }

    @Operation(summary = "Pesquisar", description = "Pesquisar pessoas para Select2", tags = {"Select2"})
    @GetMapping("/select2")
    public ResponseEntity<List<Select2OptionsDTO>> findAllToSelect2(
            @RequestParam(name = "q", required = false, defaultValue = "") String searchTerm
    ) {
        return ResponseEntity.ok(usuarioService.findAllToSelect2(searchTerm));
    }

    @GetMapping("/{id}")
    @JsonView(AppGroup.ResponsePage.class)
    public ResponseEntity<UsuarioDTO> findById(@PathVariable("id") UUID id) throws BadRequestException {
        return ResponseEntity.ok(usuarioService.findById(id));
    }

    @Operation(summary = "Ativar", description = "Ativar pessoa")
    @PutMapping({"/{id}/ativar"})
    public ResponseEntity<?> enable(@PathVariable("id") UUID id) {
        this.usuarioService.changeStatus(id, true);
        return ResponseEntity.ok(null);
    }

    @Operation(summary = "Desativar", description = "Desativar pessoa")
    @PutMapping({"/{id}/desativar"})
    public ResponseEntity<?> disable(@PathVariable("id") UUID id) {
        this.usuarioService.changeStatus(id, false);
        return ResponseEntity.ok(null);
    }

    @Operation(summary = "Cadastrar", description = "Cadastrar")
    @JsonView(AppGroup.Response.class)
    @PostMapping
    public ResponseEntity<UsuarioDTO> create(
            @Validated(AppGroup.Request.class)
            @RequestBody
            @JsonView(AppGroup.Request.class)
            UsuarioDTO usuarioDTO
    ) {
        return ResponseEntity.ok(usuarioService.create(usuarioDTO));
    }

    @Operation(summary = "Editar", description = "Editar")
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> update(
            @PathVariable("id") UUID id,
            @Validated(AppGroup.Request.class)
            @RequestBody
            @JsonView(AppGroup.Request.class)
            UsuarioDTO usuarioDTO
    ) throws BadRequestException {
        usuarioDTO.setId(id);

        return ResponseEntity.ok(usuarioService.update(usuarioDTO));
    }

    @Operation(summary = "Deletar", description = "Deletar")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) throws BadRequestException {
        usuarioService.delete(id);

        return ResponseEntity.ok().build();
    }
}
