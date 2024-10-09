package com.apiathletevision.apiathletevision.controllers;

import com.apiathletevision.apiathletevision.dtos.entities.AlunoDTO;
import com.apiathletevision.apiathletevision.dtos.responses.AlunoResponseDTO;
import com.apiathletevision.apiathletevision.services.impl.AlunoServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/aluno")
@RequiredArgsConstructor
@Tag(name = "Aluno")
public class AlunoController {

    private final AlunoServiceImpl alunoServiceImpl;

    @PostMapping
    public ResponseEntity<AlunoResponseDTO> createAluno(@RequestBody AlunoDTO alunoDTO) {
        AlunoResponseDTO aluno = alunoServiceImpl.createAluno(alunoDTO);

        return new ResponseEntity<>(aluno, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoResponseDTO> updateAluno(@PathVariable("id") UUID id, @RequestBody AlunoDTO alunoDTO) {
        AlunoResponseDTO aluno = alunoServiceImpl.updateAluno(id, alunoDTO);

        if (aluno != null) {
            return new ResponseEntity<>(aluno, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAluno(@PathVariable("id") UUID id) {
        alunoServiceImpl.deleteAluno(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponseDTO> getAlunoById(@PathVariable("id") UUID id) {
        return alunoServiceImpl.getAlunoById(id)
                .map(aluno -> new ResponseEntity<>(aluno, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<AlunoResponseDTO>> getAllAlunos() {
        List<AlunoResponseDTO> alunos = alunoServiceImpl.getAllAlunos();
        return new ResponseEntity<>(alunos, HttpStatus.OK);
    }
}