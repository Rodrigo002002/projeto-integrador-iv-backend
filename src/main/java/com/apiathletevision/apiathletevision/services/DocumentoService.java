package com.apiathletevision.apiathletevision.services;

import com.apiathletevision.apiathletevision.dtos.entities.DocumentoDTO;
import com.apiathletevision.apiathletevision.dtos.response.PageDTO;
import com.apiathletevision.apiathletevision.entities.Documento;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface DocumentoService {
    PageDTO<Documento, DocumentoDTO> findAll(int pageNo, int pageSize, String search);

    DocumentoDTO create(DocumentoDTO documentoDTO);

    List<DocumentoDTO> findAllById(List<Integer> ids) throws BadRequestException;

    DocumentoDTO update(DocumentoDTO documentoDTO) throws BadRequestException;

    void delete(int id) throws BadRequestException;
}
