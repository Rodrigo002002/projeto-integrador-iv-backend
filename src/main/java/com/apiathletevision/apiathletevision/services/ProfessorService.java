package com.apiathletevision.apiathletevision.services;

import com.apiathletevision.apiathletevision.dtos.entities.AulaDTO;
import com.apiathletevision.apiathletevision.dtos.entities.ProfessorDTO;
import com.apiathletevision.apiathletevision.dtos.entities.ServicoDTO;
import com.apiathletevision.apiathletevision.dtos.entities.TurmaDTO;
import com.apiathletevision.apiathletevision.dtos.response.PageDTO;
import com.apiathletevision.apiathletevision.dtos.select2.Select2OptionsDTO;
import com.apiathletevision.apiathletevision.entities.Aula;
import com.apiathletevision.apiathletevision.entities.Professor;
import org.apache.coyote.BadRequestException;

import java.util.List;
import java.util.UUID;

public interface ProfessorService {
    PageDTO<Professor, ProfessorDTO> findAll(int pageNo, int pageSize, String search, Boolean status);

    ProfessorDTO create(ProfessorDTO professorDTO);

    ProfessorDTO update(ProfessorDTO professorDTO) throws BadRequestException;

    ProfessorDTO findById(UUID id) throws BadRequestException;

    void delete(UUID id) throws BadRequestException;

    void changeStatus(UUID var1, boolean var2);

    List<Select2OptionsDTO> findAllToSelect2(String searchTerm);

    List<ServicoDTO> findAllServicoByProfessorID(UUID id) throws BadRequestException;

    List<TurmaDTO> findAllTurmaByProfessorID(UUID id) throws BadRequestException;

    List<AulaDTO> findAllAulaByProfessorID(UUID id) throws BadRequestException;
}
