package com.apiathletevision.apiathletevision.services;

import com.apiathletevision.apiathletevision.dtos.entities.AlunoDTO;
import com.apiathletevision.apiathletevision.dtos.response.PageDTO;
import com.apiathletevision.apiathletevision.dtos.select2.Select2OptionsDTO;
import com.apiathletevision.apiathletevision.entities.Aluno;
import org.apache.coyote.BadRequestException;

import java.util.List;
import java.util.UUID;

public interface AlunoService {
    PageDTO<Aluno, AlunoDTO> findAll(int pageNo, int pageSize, String search, Boolean status);

    AlunoDTO create(AlunoDTO alunoDTO);

    AlunoDTO update(AlunoDTO alunoDTO) throws BadRequestException;

    AlunoDTO findById(UUID id) throws BadRequestException;

    void delete(UUID id) throws BadRequestException;

    void changeStatus(UUID var1, boolean var2);

    List<Select2OptionsDTO> findAllToSelect2(String searchTerm);
}
