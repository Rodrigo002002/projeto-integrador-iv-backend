package com.apiathletevision.apiathletevision.services;

import com.apiathletevision.apiathletevision.dtos.entities.ModalidadeDTO;
import com.apiathletevision.apiathletevision.dtos.entities.PlanoDTO;
import com.apiathletevision.apiathletevision.dtos.entities.TurmaDTO;
import com.apiathletevision.apiathletevision.dtos.response.PageDTO;
import com.apiathletevision.apiathletevision.dtos.select2.Select2OptionsDTO;
import com.apiathletevision.apiathletevision.entities.Modalidade;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface ModalidadeService {
    PageDTO<Modalidade, ModalidadeDTO> findAll(int pageNo, int pageSize, String search, Boolean status);

    ModalidadeDTO create(ModalidadeDTO modalidadeDTO);

    ModalidadeDTO update(ModalidadeDTO modalidadeDTO) throws BadRequestException;

    ModalidadeDTO findById(int id) throws BadRequestException;

    void delete(int id) throws BadRequestException;

    void changeStatus(int var1, boolean var2);

    List<Select2OptionsDTO> findAllToSelect2(String searchTerm);

    List<PlanoDTO> findAllPlanoByModalidadeId(int id) throws BadRequestException;

    List<TurmaDTO> findAllTurmaByModalidadeId(int id) throws BadRequestException;
}
