package com.apiathletevision.apiathletevision.services.impl;

import com.apiathletevision.apiathletevision.dtos.entities.DocumentoDTO;
import com.apiathletevision.apiathletevision.dtos.response.PageDTO;
import com.apiathletevision.apiathletevision.entities.Aluno;
import com.apiathletevision.apiathletevision.entities.Documento;
import com.apiathletevision.apiathletevision.exeptions.BadRequestException;
import com.apiathletevision.apiathletevision.mappers.DocumentoMapper;
import com.apiathletevision.apiathletevision.repositories.DocumentoRepository;
import com.apiathletevision.apiathletevision.services.DocumentoService;
import com.apiathletevision.apiathletevision.services.specifications.DocumentoSpecification;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DocumentoServiceImpl implements DocumentoService {

    private final DocumentoRepository documentoRepository;
    private final DocumentoMapper documentoMapper;

    public PageDTO<Documento, DocumentoDTO> findAll(int pageNo, int pageSize, String search) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("imagem"));
        Specification<Documento> specification = DocumentoSpecification.getFilters(search);
        Page<Documento> documentoPage = documentoRepository.findAll(specification, pageable);
        return new PageDTO<>(documentoPage, documentoMapper::toDto);
    }

    @Override
    public List<DocumentoDTO> findAllById(List<Integer> ids) throws BadRequestException {
        List<Documento> documentos = documentoRepository.findAllById(ids);
        return documentoMapper.toDtoList(documentos);
    }

    @Override
    @Transactional
    public DocumentoDTO create(DocumentoDTO documentoDTO) {
        Documento documento = documentoMapper.toEntity(documentoDTO);
        documento = documentoRepository.save(documento);
        return documentoMapper.toDto(documento);
    }

    @Override
    @Transactional
    public DocumentoDTO update(DocumentoDTO documentoDTO) throws BadRequestException {
        Documento documento = getDocumento(documentoDTO.getId());
        documentoMapper.partialUpdate(documentoDTO, documento);
        documento = documentoRepository.save(documento);
        return documentoMapper.toDto(documento);
    }

    @Override
    public void delete(int id) throws BadRequestException {
        Documento documento = getDocumento(id);
        documentoRepository.delete(documento);
    }

    private Documento getDocumento(int id) throws BadRequestException {
        return documentoRepository.findById(id).orElseThrow(() ->
                new BadRequestException("Documento com o ID: " + id + " não encontrado"));
    }
}