package com.apiathletevision.apiathletevision.services.impl;

import com.apiathletevision.apiathletevision.dtos.entities.GestorDTO;
import com.apiathletevision.apiathletevision.dtos.response.PageDTO;
import com.apiathletevision.apiathletevision.entities.Gestor;
import com.apiathletevision.apiathletevision.exeptions.BadRequestException;
import com.apiathletevision.apiathletevision.mappers.GestorMapper;
import com.apiathletevision.apiathletevision.repositories.GestorRepository;
import com.apiathletevision.apiathletevision.services.GestorService;
import com.apiathletevision.apiathletevision.services.specifications.GestorSpecification;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class GestorServiceImpl implements GestorService {

    private final GestorRepository gestorRepository;
    private final GestorMapper gestorMapper;

    @Override
    public PageDTO<Gestor, GestorDTO> findAll(int pageNo, int pageSize, String search, Boolean status) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("nome"));
        Specification<Gestor> specification = GestorSpecification.getFilters(search, status);
        Page<Gestor> gestorPage = gestorRepository.findAll(specification, pageable);
        return new PageDTO<>(gestorPage, gestorMapper::toDto);
    }

    @Override
    @Transactional
    public GestorDTO create(GestorDTO gestorDTO) {
        Gestor gestor = gestorMapper.toEntity(gestorDTO);

        String encryptedPassword = new BCryptPasswordEncoder().encode(gestorDTO.getPassword());
        gestor.setPassword(encryptedPassword);
        gestor.setLogin(gestorDTO.getLogin());
        gestor.setStatus(gestorDTO.getStatus());

        gestor = gestorRepository.save(gestor);
        return gestorMapper.toDto(gestor);
    }

    @Override
    public GestorDTO update(GestorDTO gestorDTO) throws BadRequestException {
        Gestor gestor = getGestor(gestorDTO.getId());
        String encryptedPassword = new BCryptPasswordEncoder().encode(gestorDTO.getPassword());

        gestorMapper.partialUpdate(gestorDTO, gestor);
        gestor.setPassword(encryptedPassword);

        gestor = gestorRepository.save(gestor);
        return gestorMapper.toDto(gestor);
    }

    @Override
    public GestorDTO findById(UUID id) throws BadRequestException {
        Gestor gestor = getGestor(id);
        return gestorMapper.toDto(gestor);
    }

    @Override
    public void delete(UUID id) throws BadRequestException {
        Gestor gestor = getGestor(id);
        if (!gestor.getStatus()) {
            gestorRepository.delete(gestor);
        }
        throw new BadRequestException("Gestor ainda ativo");
    }

    @Override
    public void changeStatus(UUID id, boolean status) {
        Optional<Gestor> optionalGestor = gestorRepository.findById(id);
        if (optionalGestor.isPresent()) {
            Gestor gestor = optionalGestor.get();
            gestor.setStatus(status);
            gestorRepository.save(gestor);
        }
    }

    private Gestor getGestor(UUID id) throws BadRequestException {
        return gestorRepository.findById(id).orElseThrow(() ->
                new BadRequestException("Gestor com o ID: " + id + " não encontrado"));
    }
}