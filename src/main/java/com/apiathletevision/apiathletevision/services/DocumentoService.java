package com.apiathletevision.apiathletevision.services;

import com.apiathletevision.apiathletevision.dtos.DocumentoDTO;
import com.apiathletevision.apiathletevision.entities.Documento;
import com.apiathletevision.apiathletevision.repositories.DocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentoService {

    @Autowired
    private DocumentoRepository documentoRepository;

    public List<Documento> getAllDocumentos() {
        return documentoRepository.findAll();
    }

    public Optional<Documento> getDocumentoById(Integer id) {
        return documentoRepository.findById(id);
    }

    public Documento createDocumento(DocumentoDTO documentoDTO) {
        Documento documento = new Documento();
        documento.setTipo(documentoDTO.getTipo());
        documento.setImagem(documentoDTO.getImagem());
        return documentoRepository.save(documento);
    }

    public Documento updateDocumento(Integer id, DocumentoDTO documentoDTO) {
        Optional<Documento> optionalDocumento = documentoRepository.findById(id);
        if (optionalDocumento.isPresent()) {
            Documento documento = optionalDocumento.get();
            documento.setTipo(documentoDTO.getTipo());
            documento.setImagem(documentoDTO.getImagem());
            return documentoRepository.save(documento);
        }
        return null;
    }

    public void deleteDocumento(Integer id) {
        documentoRepository.deleteById(id);
    }
}