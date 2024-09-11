package com.apiathletevision.apiathletevision.services;

import com.apiathletevision.apiathletevision.dtos.DocumentoDTO;
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
        DocumentoDTO documentoDTO = modelMapper.map(documento, DocumentoDTO.class);
        return Optional.ofNullable(documentoDTO);
    }

    public DocumentoDTO createDocumento(DocumentoDTO documentoDTO) {
        Documento documento = new Documento();
        documento.setTipo(documentoDTO.getTipo());
        documento.setImagem(documentoDTO.getImagem());
        documentoRepository.save(documento);

        return modelMapper.map(documento, DocumentoDTO.class);
    }

    public DocumentoDTO updateDocumento(Integer id, DocumentoDTO documentoDTO) {
        Optional<Documento> optionalDocumento = documentoRepository.findById(id);
        if (optionalDocumento.isPresent()) {
            Documento documento = optionalDocumento.get();
            documento.setTipo(documentoDTO.getTipo());
            documento.setImagem(documentoDTO.getImagem());
            documentoRepository.save(documento);

            return modelMapper.map(documento, DocumentoDTO.class);
        }
        return null;
    }

    public void deleteDocumento(Integer id) {
        documentoRepository.deleteById(id);
    }
}