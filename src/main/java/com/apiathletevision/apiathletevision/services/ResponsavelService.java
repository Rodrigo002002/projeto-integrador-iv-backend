package com.apiathletevision.apiathletevision.services;

import com.apiathletevision.apiathletevision.dtos.entities.ResponsavelDTO;
import com.apiathletevision.apiathletevision.dtos.response.PageDTO;
import com.apiathletevision.apiathletevision.entities.Responsavel;
import org.apache.coyote.BadRequestException;

import java.util.UUID;

public interface ResponsavelService {
    PageDTO<Responsavel, ResponsavelDTO> findAll(int pageNo, int pageSize, String search, Boolean status);

    ResponsavelDTO create(ResponsavelDTO responsavelDTO);

    ResponsavelDTO update(ResponsavelDTO responsavelDTO) throws BadRequestException;

    ResponsavelDTO findById(UUID id) throws BadRequestException;

    void delete(UUID id) throws BadRequestException;

    void changeStatus(UUID var1, boolean var2);
}
