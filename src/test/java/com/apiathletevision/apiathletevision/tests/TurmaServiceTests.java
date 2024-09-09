package com.apiathletevision.apiathletevision.tests;

import com.apiathletevision.apiathletevision.dtos.TurmaDTO;
import com.apiathletevision.apiathletevision.entities.Turma;
import com.apiathletevision.apiathletevision.repositories.TurmaRepository;
import com.apiathletevision.apiathletevision.services.TurmaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@Transactional
public class TurmaServiceTests {

    @Mock
    private TurmaRepository turmaRepository;

    @InjectMocks
    private TurmaService turmaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateTurma() {
        TurmaDTO turmaDTO = new TurmaDTO();
        turmaDTO.setHorario(LocalDateTime.now());

        Turma turma = new Turma();
        turma.setHorario(turmaDTO.getHorario());

        when(turmaRepository.save(any(Turma.class))).thenReturn(turma);

        Turma createdTurma = turmaService.createTurma(turmaDTO);
        assertNotNull(createdTurma);
        assertEquals(turmaDTO.getHorario(), createdTurma.getHorario());
        verify(turmaRepository, times(1)).save(any(Turma.class));
    }

    @Test
    public void testGetAllTurmas() {
        Turma turma1 = new Turma();
        Turma turma2 = new Turma();
        when(turmaRepository.findAll()).thenReturn(Arrays.asList(turma1, turma2));

        List<Turma> turmas = turmaService.getAllTurmas();
        assertNotNull(turmas);
        assertEquals(2, turmas.size());
        verify(turmaRepository, times(1)).findAll();
    }

    @Test
    public void testGetTurmaById() {
        Turma turma = new Turma();
        when(turmaRepository.findById(1)).thenReturn(Optional.of(turma));

        Optional<Turma> foundTurma = turmaService.getTurmaById(1);
        assertTrue(foundTurma.isPresent());
        assertEquals(turma, foundTurma.get());
        verify(turmaRepository, times(1)).findById(1);
    }

    @Test
    public void testUpdateTurma() {
        TurmaDTO turmaDTO = new TurmaDTO();
        turmaDTO.setHorario(LocalDateTime.now());

        Turma existingTurma = new Turma();
        existingTurma.setHorario(LocalDateTime.now().minusDays(1));

        when(turmaRepository.findById(1)).thenReturn(Optional.of(existingTurma));
        when(turmaRepository.save(any(Turma.class))).thenReturn(existingTurma);

        Turma updatedTurma = turmaService.updateTurma(1, turmaDTO);
        assertNotNull(updatedTurma);
        assertEquals(turmaDTO.getHorario(), updatedTurma.getHorario());
        verify(turmaRepository, times(1)).findById(1);
        verify(turmaRepository, times(1)).save(any(Turma.class));
    }

    @Test
    public void testDeleteTurma() {
        doNothing().when(turmaRepository).deleteById(1);

        turmaService.deleteTurma(1);
        verify(turmaRepository, times(1)).deleteById(1);
    }
}