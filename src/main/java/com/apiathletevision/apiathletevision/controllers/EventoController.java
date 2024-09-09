package com.apiathletevision.apiathletevision.controllers;

import com.apiathletevision.apiathletevision.dtos.EventoDTO;
import com.apiathletevision.apiathletevision.entities.Evento;
import com.apiathletevision.apiathletevision.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/evento")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @GetMapping
    public List<Evento> getAllEventos() {
        return eventoService.getAllEventos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> getEventoById(@PathVariable Integer id) {
        Optional<Evento> evento = eventoService.getEventoById(id);
        return evento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Evento createEvento(@RequestBody EventoDTO eventoDTO) {
        return eventoService.createEvento(eventoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> updateEvento(@PathVariable Integer id, @RequestBody EventoDTO eventoDTO) {
        Evento updatedEvento = eventoService.updateEvento(id, eventoDTO);
        return updatedEvento != null ? ResponseEntity.ok(updatedEvento) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvento(@PathVariable Integer id) {
        eventoService.deleteEvento(id);
        return ResponseEntity.noContent().build();
    }
}