package com.apiathletevision.apiathletevision.controllers;

import com.apiathletevision.apiathletevision.dtos.entities.*;
import com.apiathletevision.apiathletevision.dtos.groups.AppGroup;
import com.apiathletevision.apiathletevision.dtos.response.PageDTO;
import com.apiathletevision.apiathletevision.dtos.select2.Select2OptionsDTO;
import com.apiathletevision.apiathletevision.entities.Aluno;
import com.apiathletevision.apiathletevision.services.AlunoService;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Alunos")
@RestController
@RequestMapping("${api-prefix}/alunos")
@RequiredArgsConstructor
public class AlunoController {

    private final AlunoService alunoService;

    @GetMapping
    @Operation(summary = "Listar", description = "Listar")
    @JsonView(AppGroup.ResponsePage.class)
    public ResponseEntity<PageDTO<Aluno, AlunoDTO>> findAll(
            @RequestParam(name = "pageNo", defaultValue = "0") int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(name = "search", required = false) String search,
            @RequestParam(name = "status", required = false) Boolean status
    ) {
        return ResponseEntity.ok(alunoService.findAll(pageNo, pageSize, search, status));
    }

    @Operation(summary = "Pesquisar", description = "Pesquisar alunos para Select2", tags = {"Select2"})
    @GetMapping("/select2")
    public ResponseEntity<List<Select2OptionsDTO>> findAllToSelect2(
            @RequestParam(name = "q", required = false, defaultValue = "") String searchTerm
    ) {
        return ResponseEntity.ok(alunoService.findAllToSelect2(searchTerm));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoDTO> findById(@PathVariable("id") UUID id) throws BadRequestException {
        return ResponseEntity.ok(alunoService.findById(id));
    }

    @Operation(summary = "Cadastrar", description = "Cadastrar")
    @JsonView({AppGroup.Response.class})
    @PostMapping
    public ResponseEntity<AlunoDTO> create(
            @Validated(AppGroup.Request.class)
            @RequestBody
            @JsonView(AppGroup.Request.class)
            AlunoDTO alunoDTO
    ) {
        return ResponseEntity.ok(alunoService.create(alunoDTO));
    }

    @Operation(summary = "Editar", description = "Editar")
    @PutMapping("/{id}")
    public ResponseEntity<AlunoDTO> update(
            @PathVariable("id") UUID id,
            @Validated(AppGroup.Request.class)
            @RequestBody
            @JsonView(AppGroup.Request.class)
            AlunoDTO alunoDTO
    ) throws BadRequestException {
        alunoDTO.setId(id);

        return ResponseEntity.ok(alunoService.update(alunoDTO));
    }

    @Operation(summary = "Deletar", description = "Deletar")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) throws BadRequestException {
        alunoService.delete(id);

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Ativar", description = "Ativar aluno")
    @PutMapping({"/{id}/ativar"})
    public ResponseEntity<?> enable(@PathVariable("id") UUID id) {
        this.alunoService.changeStatus(id, true);
        return ResponseEntity.ok(null);
    }

    @Operation(summary = "Desativar", description = "Desativar aluno")
    @PutMapping({"/{id}/desativar"})
    public ResponseEntity<?> disable(@PathVariable("id") UUID id) {
        this.alunoService.changeStatus(id, false);
        return ResponseEntity.ok(null);
    }

    @Operation(summary = "Pagamentos", description = "Buscar pagamentos")
    @GetMapping("/{id}/pagamentos")
    public ResponseEntity<List<PagamentoDTO>> findAllPagamentoByAlunoId(@PathVariable("id") UUID id) throws BadRequestException  {
        return ResponseEntity.ok(alunoService.findAllPagamentoByAlunoId(id));
    }

    @Operation(summary = "Turmas", description = "Buscar turmas")
    @GetMapping("/{id}/turmas")
    public ResponseEntity<List<TurmaDTO>> findAllTurmaByAlunoId(@PathVariable("id") UUID id) throws BadRequestException  {
        return ResponseEntity.ok(alunoService.findAllTurmaByAlunoId(id));
    }

    @Operation(summary = "Servicos", description = "Buscar servicos")
    @GetMapping("/{id}/servicos")
    public ResponseEntity<List<ServicoDTO>> findAllServicoByAlunoId(@PathVariable("id") UUID id) throws BadRequestException  {
        return ResponseEntity.ok(alunoService.findAllServicoByAlunoId(id));
    }

    @Operation(summary = "Aulas", description = "Buscar aulas")
    @GetMapping("/{id}/aulas")
    public ResponseEntity<List<AulaDTO>> findAllAulaByAlunoId(@PathVariable("id") UUID id) throws BadRequestException  {
        return ResponseEntity.ok(alunoService.findAllAulaByAlunoId(id));
    }

    @Operation(summary = "Pagamentos", description = "Buscar pagamentos por plano")
    @GetMapping("/{id}/{planoId}/pagamentos")
    public ResponseEntity<List<PagamentoDTO>> findAllPagamentoByAlunoIdAndPlanoId(
            @PathVariable("id") UUID id,
            @PathVariable("planoId") int planoId
    ) throws BadRequestException  {
        return ResponseEntity.ok(alunoService.findAllPagamentoByAlunoIdAndPlanoId(id, planoId));
    }
}