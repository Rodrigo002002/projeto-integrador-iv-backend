package com.apiathletevision.apiathletevision.services;

import com.apiathletevision.apiathletevision.dtos.entities.PlanoDTO;
import com.apiathletevision.apiathletevision.dtos.response.PageDTO;
import com.apiathletevision.apiathletevision.dtos.select2.Select2OptionsDTO;
import com.apiathletevision.apiathletevision.entities.Plano;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface PlanoService {
    PageDTO<Plano, PlanoDTO> findAll(int pageNo, int pageSize, String search, Boolean status);

    PlanoDTO create(PlanoDTO planoDTO);

    PlanoDTO update(PlanoDTO planoDTO) throws BadRequestException;

    PlanoDTO findById(int id) throws BadRequestException;

    void delete(int id) throws BadRequestException;

    void changeStatus(int var1, boolean var2);

    List<Select2OptionsDTO> findAllToSelect2(String searchTerm);
}
