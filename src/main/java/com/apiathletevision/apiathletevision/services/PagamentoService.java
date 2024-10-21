package com.apiathletevision.apiathletevision.services;

import com.apiathletevision.apiathletevision.dtos.entities.PagamentoDTO;
import com.apiathletevision.apiathletevision.dtos.response.PageDTO;
import com.apiathletevision.apiathletevision.entities.Pagamento;
import org.apache.coyote.BadRequestException;

public interface PagamentoService {
    PageDTO<Pagamento, PagamentoDTO> findAll(int pageNo, int pageSize, String search, Boolean status);

    PagamentoDTO create(PagamentoDTO pagamentoDTO);

    PagamentoDTO update(PagamentoDTO pagamentoDTO) throws BadRequestException;

    PagamentoDTO findById(int id) throws BadRequestException;

    void delete(int id) throws BadRequestException;

    void changeStatus(int var1, boolean var2);
}
