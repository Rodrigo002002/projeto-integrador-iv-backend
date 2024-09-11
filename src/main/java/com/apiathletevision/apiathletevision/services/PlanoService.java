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
        PlanoDTO planoDTO = modelMapper.map(plano, PlanoDTO.class);
        return Optional.ofNullable(planoDTO);
    }

    public PlanoDTO createPlano(PlanoDTO planoDTO) {
        Plano plano = new Plano();

        modelMapper.map(planoDTO, plano);

        plano.setModalidades(modalidadeRepository.findAllById(planoDTO.getModalidadesIds()));
        plano.setPagamentos(pagamentoRepository.findAllById(planoDTO.getPagamentosIds()));
        plano = planoRepository.save(plano);

        return modelMapper.map(plano, PlanoDTO.class);
    }

    public PlanoDTO updatePlano(Integer id, PlanoDTO planoDTO) {
        Optional<Plano> optionalPlano = planoRepository.findById(id);

        if (optionalPlano.isPresent()) {
            Plano plano = optionalPlano.get();

            plano.setId(id);
            plano.setNome(planoDTO.getNome());
            plano.setTipo(planoDTO.getTipo());
            plano.setBeneficios(planoDTO.getBeneficios());
            plano.setModalidades(modalidadeRepository.findAllById(planoDTO.getModalidadesIds()));
            plano.setPagamentos(pagamentoRepository.findAllById(planoDTO.getPagamentosIds()));
            plano.setPreco(planoDTO.getPreco());

            plano = planoRepository.save(plano);

            return modelMapper.map(plano, PlanoDTO.class);
        }
        return null;
    }

    public void deletePlano(Integer id) {
        planoRepository.deleteById(id);
    }
}