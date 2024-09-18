package com.apiathletevision.apiathletevision.services;

import com.apiathletevision.apiathletevision.dtos.GestorDTO;
import com.apiathletevision.apiathletevision.entities.Gestor;
import com.apiathletevision.apiathletevision.repositories.GestorRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GestorService {

    private final GestorRepository gestorRepository;

    private final ModelMapper modelMapper;

    public List<GestorDTO> getAllGestores() {
        return gestorRepository.findAll().stream().map(gestor -> modelMapper.map(gestor, GestorDTO.class)).toList();
    }

    public Optional<GestorDTO> getGestorById(UUID id) {
        Optional<Gestor> gestor = gestorRepository.findById(id);
        GestorDTO gestorDTO = modelMapper.map(gestor, GestorDTO.class);
        return Optional.ofNullable(gestorDTO);
    }

    public GestorDTO createGestor(GestorDTO gestorDTO) {
        Gestor gestor = new Gestor();

        gestor.setNome(gestorDTO.getNome());
        gestor.setRole(gestorDTO.getRole());
        gestor.setTelefone(gestorDTO.getTelefone());
        gestor.setEmail(gestorDTO.getEmail());
        gestor.setRg(gestorDTO.getRg());
        gestor.setCpf(gestorDTO.getCpf());

        gestor = gestorRepository.save(gestor);

        return modelMapper.map(gestor, GestorDTO.class);
    }

    public GestorDTO updateGestor(UUID id, GestorDTO gestorDTO) {
        Optional<Gestor> optionalGestor = gestorRepository.findById(id);

        if (optionalGestor.isPresent()) {
            Gestor gestor = optionalGestor.get();
            gestor.setId(id);
            gestor.setNome(gestorDTO.getNome());
            gestor.setRole(gestorDTO.getRole());
            gestor.setTelefone(gestorDTO.getTelefone());
            gestor.setEmail(gestorDTO.getEmail());
            gestor.setRg(gestorDTO.getRg());
            gestor.setCpf(gestorDTO.getCpf());

            gestor = gestorRepository.save(gestor);

            return modelMapper.map(gestor, GestorDTO.class);
        }
        return null;
    }

    public void deleteGestor(UUID id) {
        gestorRepository.deleteById(id);
    }
}