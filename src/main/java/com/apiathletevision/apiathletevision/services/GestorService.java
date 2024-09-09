package com.apiathletevision.apiathletevision.services;

import com.apiathletevision.apiathletevision.dtos.GestorDTO;
import com.apiathletevision.apiathletevision.entities.Gestor;
import com.apiathletevision.apiathletevision.repositories.GestorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GestorService {

    @Autowired
    private GestorRepository gestorRepository;

    public List<Gestor> getAllGestores() {
        return gestorRepository.findAll();
    }

    public Optional<Gestor> getGestorById(UUID id) {
        return gestorRepository.findById(id);
    }

    public Gestor createGestor(GestorDTO gestorDTO) {
        Gestor gestor = new Gestor();
        gestor.setNome(gestorDTO.getNome());
        gestor.setRole(gestorDTO.getRole());
        gestor.setTelefone(gestorDTO.getTelefone());
        gestor.setEmail(gestorDTO.getEmail());
        return gestorRepository.save(gestor);
    }

    public Gestor updateGestor(UUID id, GestorDTO gestorDTO) {
        Optional<Gestor> optionalGestor = gestorRepository.findById(id);
        if (optionalGestor.isPresent()) {
            Gestor gestor = optionalGestor.get();
            gestor.setNome(gestorDTO.getNome());
            gestor.setRole(gestorDTO.getRole());
            gestor.setTelefone(gestorDTO.getTelefone());
            gestor.setEmail(gestorDTO.getEmail());
            return gestorRepository.save(gestor);
        }
        return null;
    }

    public void deleteGestor(UUID id) {
        gestorRepository.deleteById(id);
    }
}