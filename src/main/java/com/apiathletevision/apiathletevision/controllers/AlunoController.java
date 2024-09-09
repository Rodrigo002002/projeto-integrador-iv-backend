package com.apiathletevision.apiathletevision.controllers;

import com.apiathletevision.apiathletevision.dtos.AlunoDTO;
import com.apiathletevision.apiathletevision.entities.Aluno;
import com.apiathletevision.apiathletevision.services.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping
    public ResponseEntity<AlunoDTO> createAluno(@RequestBody AlunoDTO alunoDTO) {
        Aluno aluno = alunoService.createAluno(alunoDTO);
        AlunoDTO dto = convertToDTO(aluno);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoDTO> updateAluno(@PathVariable UUID id, @RequestBody AlunoDTO alunoDTO) {
        Aluno aluno = alunoService.updateAluno(id, alunoDTO);
        if (aluno != null) {
            AlunoDTO dto = convertToDTO(aluno);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAluno(@PathVariable UUID id) {
        alunoService.deleteAluno(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoDTO> getAlunoById(@PathVariable UUID id) {
        return alunoService.getAlunoById(id)
                .map(aluno -> new ResponseEntity<>(convertToDTO(aluno), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<AlunoDTO>> getAllAlunos() {
        List<Aluno> alunos = alunoService.getAllAlunos();
        List<AlunoDTO> dtos = alunos.stream()
                .map(this::convertToDTO)
                .toList();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    private AlunoDTO convertToDTO(Aluno aluno) {
        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setNome(aluno.getNome());
        alunoDTO.setRole(aluno.getRole());
        alunoDTO.setTelefone(aluno.getTelefone());
        alunoDTO.setEmail(aluno.getEmail());
        alunoDTO.setTurmaId(aluno.getTurma() != null ? aluno.getTurma().getId() : null);
        alunoDTO.setDocumentosIds(aluno.getDocumentos().stream().map(d -> d.getId()).toList());
        alunoDTO.setPlanoId(aluno.getPlano() != null ? aluno.getPlano().getId() : null);
        return alunoDTO;
    }
}