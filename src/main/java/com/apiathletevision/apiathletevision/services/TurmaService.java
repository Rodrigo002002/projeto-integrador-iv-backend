package com.apiathletevision.apiathletevision.services;

import com.apiathletevision.apiathletevision.dtos.entities.AlunoDTO;
import com.apiathletevision.apiathletevision.dtos.entities.AulaDTO;
import com.apiathletevision.apiathletevision.dtos.entities.TurmaDTO;
import com.apiathletevision.apiathletevision.dtos.response.PageDTO;
import com.apiathletevision.apiathletevision.entities.Turma;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface TurmaService {
    PageDTO<Turma, TurmaDTO> findAll(int pageNo, int pageSize, String search);

    TurmaDTO create(TurmaDTO turmaDTO);

    TurmaDTO update(TurmaDTO turmaDTO) throws BadRequestException;

    TurmaDTO findById(int id) throws BadRequestException;

    List<AulaDTO> findAllAulaByTurmaId(int id);

    void delete(int id) throws BadRequestException;
}
