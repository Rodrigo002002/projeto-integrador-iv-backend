package com.apiathletevision.apiathletevision.services;

import com.apiathletevision.apiathletevision.dtos.entities.TipoServicoDTO;
import com.apiathletevision.apiathletevision.dtos.response.PageDTO;
import com.apiathletevision.apiathletevision.dtos.select2.Select2OptionsDTO;
import com.apiathletevision.apiathletevision.entities.TipoServico;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface TipoServicoService {
    PageDTO<TipoServico, TipoServicoDTO> findAll(int pageNo, int pageSize, String search, Boolean status);

    TipoServicoDTO create(TipoServicoDTO tipoServicoDTO);

    TipoServicoDTO update(TipoServicoDTO tipoServicoDTO) throws BadRequestException;

    TipoServicoDTO findById(int id) throws BadRequestException;

    void delete(int id) throws BadRequestException;

    void changeStatus(int var1, boolean var2);

    List<Select2OptionsDTO> findAllToSelect2(String searchTerm);
}
