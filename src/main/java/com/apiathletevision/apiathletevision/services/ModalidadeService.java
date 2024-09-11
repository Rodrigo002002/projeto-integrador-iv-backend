package com.apiathletevision.apiathletevision.services;

import com.apiathletevision.apiathletevision.dtos.ModalidadeDTO;
import com.apiathletevision.apiathletevision.entities.Modalidade;
import com.apiathletevision.apiathletevision.repositories.ModalidadeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ModalidadeService {

    private final ModalidadeRepository modalidadeRepository;
    private final ModelMapper modelMapper;

    public List<ModalidadeDTO> getAllModalidades() {
        return modalidadeRepository.findAll().stream().map(modalidade -> modelMapper.map(modalidade, ModalidadeDTO.class)).toList();
    }

    public Optional<ModalidadeDTO> getModalidadeById(Integer id) {
        Optional<Modalidade> modalidade = modalidadeRepository.findById(id);
        ModalidadeDTO modalidadeDTO = modelMapper.map(modalidade, ModalidadeDTO.class);
        return Optional.ofNullable(modalidadeDTO);
    }

    public ModalidadeDTO createModalidade(ModalidadeDTO modalidadeDTO) {
        Modalidade modalidade = new Modalidade();

        modalidade.setNome(modalidadeDTO.getNome());

        modalidade = modalidadeRepository.save(modalidade);

        return modelMapper.map(modalidade, ModalidadeDTO.class);
    }

    public ModalidadeDTO updateModalidade(Integer id, ModalidadeDTO modalidadeDTO) {
        Optional<Modalidade> optionalModalidade = modalidadeRepository.findById(id);

        if (optionalModalidade.isPresent()) {

            Modalidade modalidade = optionalModalidade.get();
            modalidade.setId(id);
            modalidade.setNome(modalidadeDTO.getNome());

            modalidade = modalidadeRepository.save(modalidade);
            return modelMapper.map(modalidade, ModalidadeDTO.class);
        }
        return null;
    }

    public void deleteModalidade(Integer id) {
        modalidadeRepository.deleteById(id);
    }
}