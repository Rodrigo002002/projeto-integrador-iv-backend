package com.apiathletevision.apiathletevision.services;

import com.apiathletevision.apiathletevision.dtos.PlanoDTO;
import com.apiathletevision.apiathletevision.entities.Plano;
import com.apiathletevision.apiathletevision.repositories.ModalidadeRepository;
import com.apiathletevision.apiathletevision.repositories.PagamentoRepository;
import com.apiathletevision.apiathletevision.repositories.PlanoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlanoService {

    private final ModelMapper modelMapper;
    private final PlanoRepository planoRepository;
    private final ModalidadeRepository modalidadeRepository;
    private final PagamentoRepository pagamentoRepository;

    public List<PlanoDTO> getAllPlanos() {
        return planoRepository.findAll().stream().map(plano -> modelMapper.map(plano, PlanoDTO.class)).toList();
    }

    public Optional<PlanoDTO> getPlanoById(Integer id) {
        Optional<Plano> plano = planoRepository.findById(id);
        PlanoDTO planoRequestDTO = modelMapper.map(plano, PlanoDTO.class);
        return Optional.ofNullable(planoRequestDTO);
    }

    public PlanoDTO createPlano(PlanoDTO planoRequestDTO) {
        Plano plano = new Plano();

        modelMapper.map(planoRequestDTO, plano);

        plano.setModalidades(modalidadeRepository.findAllById(planoRequestDTO.getModalidadesIds()));
        plano.setPagamentos(pagamentoRepository.findAllById(planoRequestDTO.getPagamentosIds()));
        plano = planoRepository.save(plano);

        return modelMapper.map(plano, PlanoDTO.class);
    }

    public PlanoDTO updatePlano(Integer id, PlanoDTO planoRequestDTO) {
        Optional<Plano> optionalPlano = planoRepository.findById(id);

        if (optionalPlano.isPresent()) {
            Plano plano = optionalPlano.get();

            plano.setId(id);
            plano.setNome(planoRequestDTO.getNome());
            plano.setTipo(planoRequestDTO.getTipo());
            plano.setBeneficios(planoRequestDTO.getBeneficios());
            plano.setModalidades(modalidadeRepository.findAllById(planoRequestDTO.getModalidadesIds()));
            plano.setPagamentos(pagamentoRepository.findAllById(planoRequestDTO.getPagamentosIds()));
            plano.setPreco(planoRequestDTO.getPreco());

            plano = planoRepository.save(plano);

            return modelMapper.map(plano, PlanoDTO.class);
        }
        return null;
    }

    public void deletePlano(Integer id) {
        planoRepository.deleteById(id);
    }
}