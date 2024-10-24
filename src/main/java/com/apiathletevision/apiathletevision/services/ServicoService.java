package com.apiathletevision.apiathletevision.services;

import com.apiathletevision.apiathletevision.dtos.entities.PagamentoDTO;
import com.apiathletevision.apiathletevision.dtos.entities.ServicoDTO;
import com.apiathletevision.apiathletevision.dtos.response.PageDTO;
import com.apiathletevision.apiathletevision.dtos.select2.Select2OptionsDTO;
import com.apiathletevision.apiathletevision.entities.Servico;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface ServicoService {
    PageDTO<Servico, ServicoDTO> findAll(int pageNo, int pageSize, String search, Boolean status);

    ServicoDTO create(ServicoDTO servicoDTO);

    ServicoDTO update(ServicoDTO servicoDTO) throws BadRequestException;

    ServicoDTO findById(int id) throws BadRequestException;

    void delete(int id) throws BadRequestException;

    void changeStatus(int var1, boolean var2);

    List<PagamentoDTO> findAllPagamentoByServicoId(int id);

    List<Select2OptionsDTO> findAllToSelect2(String searchTerm);
}
