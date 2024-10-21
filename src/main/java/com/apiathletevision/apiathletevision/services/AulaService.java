package com.apiathletevision.apiathletevision.services;

import com.apiathletevision.apiathletevision.dtos.entities.AulaDTO;
import com.apiathletevision.apiathletevision.dtos.response.PageDTO;
import com.apiathletevision.apiathletevision.entities.Aula;
import org.apache.coyote.BadRequestException;

public interface AulaService {
    PageDTO<Aula, AulaDTO> findAll(int pageNo, int pageSize, String search, Boolean status);

    AulaDTO create(AulaDTO aulaDTO);

    AulaDTO update(AulaDTO aulaDTO) throws BadRequestException;

    AulaDTO findById(int id) throws BadRequestException;

    void delete(int id) throws BadRequestException;

    void changeStatus(int var1, boolean var2);
}
