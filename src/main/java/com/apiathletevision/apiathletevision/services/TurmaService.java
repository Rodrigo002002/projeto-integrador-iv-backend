package com.apiathletevision.apiathletevision.services;

import com.apiathletevision.apiathletevision.dtos.entities.TurmaDTO;
import com.apiathletevision.apiathletevision.dtos.response.PageDTO;
import com.apiathletevision.apiathletevision.entities.Turma;
import org.apache.coyote.BadRequestException;

public interface TurmaService {
    PageDTO<Turma, TurmaDTO> findAll(int pageNo, int pageSize, String search);

    TurmaDTO create(TurmaDTO turmaDTO);

    TurmaDTO update(TurmaDTO turmaDTO) throws BadRequestException;

    TurmaDTO findById(int id) throws BadRequestException;

    void delete(int id) throws BadRequestException;
}
