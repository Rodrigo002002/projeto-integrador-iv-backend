package com.apiathletevision.apiathletevision.services;

import com.apiathletevision.apiathletevision.dtos.entities.EventoDTO;
import com.apiathletevision.apiathletevision.dtos.response.PageDTO;
import com.apiathletevision.apiathletevision.entities.Evento;
import org.apache.coyote.BadRequestException;

public interface EventoService {
    PageDTO<Evento, EventoDTO> findAll(int pageNo, int pageSize, String search, Boolean status);

    EventoDTO create(EventoDTO eventoDTO);

    EventoDTO update(EventoDTO eventoDTO) throws BadRequestException;

    EventoDTO findById(int id) throws BadRequestException;

    void delete(int id) throws BadRequestException;

    void changeStatus(int var1, boolean var2);
}
