package com.apiathletevision.apiathletevision.services;

import com.apiathletevision.apiathletevision.dtos.ModalidadeDTO;
import com.apiathletevision.apiathletevision.entities.Modalidade;
import com.apiathletevision.apiathletevision.repositories.ModalidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModalidadeService {

    @Autowired
    private ModalidadeRepository modalidadeRepository;

    public List<Modalidade> getAllModalidades() {
        return modalidadeRepository.findAll();
    }

    public Optional<Modalidade> getModalidadeById(Integer id) {
        return modalidadeRepository.findById(id);
    }

    public Modalidade createModalidade(ModalidadeDTO modalidadeDTO) {
        Modalidade modalidade = new Modalidade();
        modalidade.setNome(modalidadeDTO.getNome());
        return modalidadeRepository.save(modalidade);
    }

    public Modalidade updateModalidade(Integer id, ModalidadeDTO modalidadeDTO) {
        Optional<Modalidade> optionalModalidade = modalidadeRepository.findById(id);
        if (optionalModalidade.isPresent()) {
            Modalidade modalidade = optionalModalidade.get();
            modalidade.setNome(modalidadeDTO.getNome());
            return modalidadeRepository.save(modalidade);
        }
        return null;
    }

    public void deleteModalidade(Integer id) {
        modalidadeRepository.deleteById(id);
    }
}