package com.apiathletevision.apiathletevision.controllers;

import com.apiathletevision.apiathletevision.dtos.entities.EventoDTO;
import com.apiathletevision.apiathletevision.dtos.groups.AppGroup;
import com.apiathletevision.apiathletevision.dtos.response.PageDTO;
import com.apiathletevision.apiathletevision.entities.Evento;
import com.apiathletevision.apiathletevision.services.EventoService;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Eventos")
@RestController
@RequestMapping("${api-prefix}/eventos")
@RequiredArgsConstructor
public class EventoController {

    private final EventoService eventoService;

    @GetMapping
    @Operation(summary = "Listar", description = "Listar")
    @JsonView(AppGroup.ResponsePage.class)
    public ResponseEntity<PageDTO<Evento, EventoDTO>> findAll(
            @RequestParam(name = "pageNo", defaultValue = "0") int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(name = "search", required = false) String search,
            @RequestParam(name = "status", required = false) Boolean status
    ) {
        return ResponseEntity.ok(eventoService.findAll(pageNo, pageSize, search, status));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoDTO> findById(@PathVariable("id") int id) throws BadRequestException {
        return ResponseEntity.ok(eventoService.findById(id));
    }

    @Operation(summary = "Cadastrar", description = "Cadastrar")
    @JsonView({AppGroup.Response.class})
    @PostMapping
    public ResponseEntity<EventoDTO> create(
            @Validated(AppGroup.Request.class)
            @RequestBody
            @JsonView(AppGroup.Request.class)
            EventoDTO eventoDTO
    ) {
        return ResponseEntity.ok(eventoService.create(eventoDTO));
    }

    @Operation(summary = "Editar", description = "Editar")
    @PutMapping("/{id}")
    public ResponseEntity<EventoDTO> update(
            @PathVariable("id") int id,
            @Validated(AppGroup.Request.class)
            @RequestBody
            @JsonView(AppGroup.Request.class)
            EventoDTO eventoDTO
    ) throws BadRequestException {
        eventoDTO.setId(id);

        return ResponseEntity.ok(eventoService.update(eventoDTO));
    }

    @Operation(summary = "Deletar", description = "Deletar")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) throws BadRequestException {
        eventoService.delete(id);

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Ativar", description = "Ativar evento")
    @PutMapping({"/{id}/ativar"})
    public ResponseEntity<?> enable(@PathVariable("id") int id) {
        this.eventoService.changeStatus(id, true);
        return ResponseEntity.ok(null);
    }

    @Operation(summary = "Desativar", description = "Desativar evento")
    @PutMapping({"/{id}/desativar"})
    public ResponseEntity<?> disable(@PathVariable("id") int id) {
        this.eventoService.changeStatus(id, false);
        return ResponseEntity.ok(null);
    }
}