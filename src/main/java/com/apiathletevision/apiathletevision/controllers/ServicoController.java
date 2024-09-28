package com.apiathletevision.apiathletevision.controllers;

import com.apiathletevision.apiathletevision.dtos.ServicoDTO;
import com.apiathletevision.apiathletevision.services.ServicoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servico")
@RequiredArgsConstructor
@Tag(name = "Servico")
public class ServicoController {

    private final ServicoService servicoService;

    @GetMapping
    public ResponseEntity<List<ServicoDTO>> getAllServicos() {
        List<ServicoDTO> servicos = servicoService.getAllServicos();
        return new ResponseEntity<>(servicos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicoDTO> getServicoById(@PathVariable("id") Integer id) {
        return servicoService.getServicoById(id)
                .map(servico -> new ResponseEntity<>(servico, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ServicoDTO> createServico(@RequestBody ServicoDTO servicoRequestDTO) {
        ServicoDTO servico = servicoService.createServico(servicoRequestDTO);
        return new ResponseEntity<>(servico, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicoDTO> updateServico(@PathVariable("id") Integer id, @RequestBody ServicoDTO servicoRequestDTO) {
        ServicoDTO servico = servicoService.updateServico(id, servicoRequestDTO);

        if (servico != null) {
            return new ResponseEntity<>(servico, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServico(@PathVariable("id") Integer id) {
        servicoService.deleteServico(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}