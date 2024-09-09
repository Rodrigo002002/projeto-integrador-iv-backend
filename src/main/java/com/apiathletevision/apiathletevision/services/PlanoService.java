package com.apiathletevision.apiathletevision.services;

import com.apiathletevision.apiathletevision.dtos.PlanoDTO;
import com.apiathletevision.apiathletevision.entities.Plano;
import com.apiathletevision.apiathletevision.repositories.PlanoRepository;
import com.apiathletevision.apiathletevision.repositories.ModalidadeRepository;
import com.apiathletevision.apiathletevision.repositories.PagamentoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanoService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PlanoRepository planoRepository;

    @Autowired
    private ModalidadeRepository modalidadeRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    public List<Plano> getAllPlanos() {
        return planoRepository.findAll();
    }

    public Optional<Plano> getPlanoById(Integer id) {
        return planoRepository.findById(id);
    }

    public Plano createPlano(PlanoDTO planoDTO) {
        Plano plano = new Plano();

        modelMapper.map(planoDTO, plano);

        plano.setModalidades(modalidadeRepository.findAllById(planoDTO.getModalidadesIds()));
        plano.setPagamentos(pagamentoRepository.findAllById(planoDTO.getPagamentosIds()));
        return planoRepository.save(plano);
    }

    public Plano updatePlano(Integer id, PlanoDTO planoDTO) {
        Optional<Plano> optionalPlano = planoRepository.findById(id);
        if (optionalPlano.isPresent()) {
            Plano plano = optionalPlano.get();

            modelMapper.map(planoDTO, plano);

            plano.setModalidades(modalidadeRepository.findAllById(planoDTO.getModalidadesIds()));
            plano.setPagamentos(pagamentoRepository.findAllById(planoDTO.getPagamentosIds()));
            return planoRepository.save(plano);
        }
        return null;
    }

    public void deletePlano(Integer id) {
        planoRepository.deleteById(id);
    }
}