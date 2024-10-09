package com.apiathletevision.apiathletevision.services.impl;

import com.apiathletevision.apiathletevision.dtos.entities.DocumentoDTO;
import com.apiathletevision.apiathletevision.entities.Documento;
import com.apiathletevision.apiathletevision.repositories.DocumentoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DocumentoService {

    private final DocumentoRepository documentoRepository;

    private final ModelMapper modelMapper;

    public List<DocumentoDTO> getAllDocumentos() {
        return documentoRepository.findAll().stream().map(documento -> modelMapper.map(documento, DocumentoDTO.class)).toList();
    }

    public Optional<DocumentoDTO> getDocumentoById(Integer id) {
        Optional<Documento> documento = documentoRepository.findById(id);
        DocumentoDTO documentoRequestDTO = modelMapper.map(documento, DocumentoDTO.class);
        return Optional.ofNullable(documentoRequestDTO);
    }

    public DocumentoDTO createDocumento(DocumentoDTO documentoRequestDTO) {
        Documento documento = new Documento();
        documento.setTipo(documentoRequestDTO.getTipo());
        documento.setImagem(documentoRequestDTO.getImagem());
        documentoRepository.save(documento);

        return modelMapper.map(documento, DocumentoDTO.class);
    }

    public DocumentoDTO updateDocumento(Integer id, DocumentoDTO documentoRequestDTO) {
        Optional<Documento> optionalDocumento = documentoRepository.findById(id);
        if (optionalDocumento.isPresent()) {
            Documento documento = optionalDocumento.get();
            documento.setTipo(documentoRequestDTO.getTipo());
            documento.setImagem(documentoRequestDTO.getImagem());
            documentoRepository.save(documento);

            return modelMapper.map(documento, DocumentoDTO.class);
        }
        return null;
    }

    public void deleteDocumento(Integer id) {
        documentoRepository.deleteById(id);
    }
}