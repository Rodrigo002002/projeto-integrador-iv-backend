package com.apiathletevision.apiathletevision.services;

import com.apiathletevision.apiathletevision.dtos.entities.EquipeDTO;
import com.apiathletevision.apiathletevision.dtos.response.PageDTO;
import com.apiathletevision.apiathletevision.dtos.select2.Select2OptionsDTO;
import com.apiathletevision.apiathletevision.entities.Equipe;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface EquipeService {
    PageDTO<Equipe, EquipeDTO> findAll(int pageNo, int pageSize, String search, Boolean status);

    EquipeDTO create(EquipeDTO equipeDTO);

    EquipeDTO update(EquipeDTO equipeDTO) throws BadRequestException;

    EquipeDTO findById(int id) throws BadRequestException;

    void delete(int id) throws BadRequestException;

    void changeStatus(int var1, boolean var2);

    List<Select2OptionsDTO> findAllToSelect2(String searchTerm);
}
