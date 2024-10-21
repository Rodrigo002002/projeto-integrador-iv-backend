package com.apiathletevision.apiathletevision.services;

import com.apiathletevision.apiathletevision.dtos.entities.GestorDTO;
import com.apiathletevision.apiathletevision.dtos.response.PageDTO;
import com.apiathletevision.apiathletevision.entities.Gestor;
import org.apache.coyote.BadRequestException;

import java.util.UUID;

public interface GestorService {
    PageDTO<Gestor, GestorDTO> findAll(int pageNo, int pageSize, String search, Boolean status);

    GestorDTO create(GestorDTO gestorDTO);

    GestorDTO update(GestorDTO gestorDTO) throws BadRequestException;

    GestorDTO findById(UUID id) throws BadRequestException;

    void delete(UUID id) throws BadRequestException;

    void changeStatus(UUID var1, boolean var2);
}
