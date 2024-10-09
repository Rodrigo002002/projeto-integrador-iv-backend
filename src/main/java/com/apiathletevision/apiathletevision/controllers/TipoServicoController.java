package com.apiathletevision.apiathletevision.controllers;

import com.apiathletevision.apiathletevision.dtos.entities.TipoServicoDTO;
import com.apiathletevision.apiathletevision.services.impl.TipoServicoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipoServico")
@RequiredArgsConstructor
@Tag(name = "Tipo Servico")
public class TipoServicoController {

    private final TipoServicoService tipoServicoService;

    @GetMapping
    public ResponseEntity<List<TipoServicoDTO>> getAllTiposServico() {
        List<TipoServicoDTO> tiposServico = tipoServicoService.getAllTiposServico();
        return new ResponseEntity<>(tiposServico, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoServicoDTO> getTipoServicoById(@PathVariable("id") Integer id) {
        return tipoServicoService.getTipoServico(id)
                .map(tipoServico -> new ResponseEntity<>(tipoServico, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<TipoServicoDTO> createTipoServico(@RequestBody TipoServicoDTO TipoServicoRequestDTO) {
        TipoServicoDTO tipoServico = tipoServicoService.createTipoServico(TipoServicoRequestDTO);
        return new ResponseEntity<>(tipoServico, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoServicoDTO> updateTipoServico(@PathVariable("id") Integer id, @RequestBody TipoServicoDTO tipoServicoRequestDTO) {
        TipoServicoDTO tipoServico = tipoServicoService.updateTipoServico(id, tipoServicoRequestDTO);

        if (tipoServico != null) {
            return new ResponseEntity<>(tipoServico, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTipoServico(@PathVariable("id") Integer id) {
        tipoServicoService.deleteTipoServico(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
