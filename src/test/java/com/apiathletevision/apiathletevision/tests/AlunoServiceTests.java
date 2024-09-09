package com.apiathletevision.apiathletevision.services;

import com.apiathletevision.apiathletevision.dtos.AlunoDTO;
import com.apiathletevision.apiathletevision.dtos.DocumentoDTO;
import com.apiathletevision.apiathletevision.entities.Aluno;
import com.apiathletevision.apiathletevision.entities.Turma;
import com.apiathletevision.apiathletevision.entities.Plano;
import com.apiathletevision.apiathletevision.entities.Documento;
import com.apiathletevision.apiathletevision.enums.UserRole;
import com.apiathletevision.apiathletevision.repositories.AlunoRepository;
import com.apiathletevision.apiathletevision.repositories.TurmaRepository;
import com.apiathletevision.apiathletevision.repositories.PlanoRepository;
import com.apiathletevision.apiathletevision.repositories.DocumentoRepository;
import lombok.extern.java.Log;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@Transactional
public class AlunoServiceTests {

    @Mock
    private AlunoRepository alunoRepository;

    @Mock
    private TurmaRepository turmaRepository;

    @Mock
    private PlanoRepository planoRepository;

    @Mock
    private DocumentoService documentoService;

    @InjectMocks
    private AlunoService alunoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateAluno() {
        // Cria e salva documento
        DocumentoDTO documentoDTO = new DocumentoDTO();
        documentoDTO.setTipo("Identidade");
        documentoDTO.setImagem("identidade.jpg");
        documentoService.createDocumento(documentoDTO);

        // Cria DTO do Aluno
        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setNome("João");
        alunoDTO.setRole(UserRole.ALUNO);
        alunoDTO.setTelefone("123456789");
        alunoDTO.setEmail("joao@example.com");
        alunoDTO.setDocumentosIds(List.of(1));
        alunoDTO.setTurmaId(0);
        alunoDTO.setPlanoId(0);

        // Cria o Aluno
        alunoService.createAluno(alunoDTO);

        System.out.println(alunoService.getAllAlunos());
    }
}