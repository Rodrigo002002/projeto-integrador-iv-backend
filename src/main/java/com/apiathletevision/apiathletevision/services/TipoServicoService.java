package com.apiathletevision.apiathletevision.services;

import com.apiathletevision.apiathletevision.dtos.TipoServicoDTO;
import com.apiathletevision.apiathletevision.entities.TipoServico;
import com.apiathletevision.apiathletevision.repositories.TipoServicoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TipoServicoService {

    private final TipoServicoRepository tipoServicoRepository;

    private final ModelMapper modelMapper;

    public List<TipoServicoDTO> getAllTiposServico() {
        return tipoServicoRepository.findAll().stream().map(tipoServico -> modelMapper.map(tipoServico, TipoServicoDTO.class)).toList();
    }

    public Optional<TipoServicoDTO> getTipoServico(Integer id) {
        Optional<TipoServico> tipoServico = tipoServicoRepository.findById(id);
        TipoServicoDTO tipoServicoDTO = modelMapper.map(tipoServico, TipoServicoDTO.class);
        return Optional.ofNullable(tipoServicoDTO);
    }

    public TipoServicoDTO createTipoServico(TipoServicoDTO tipoServicoRequestDTO) {
        TipoServico tipoServico = new TipoServico();
        tipoServico.setPreco(tipoServicoRequestDTO.getPreco());
        tipoServico.setTipo(tipoServicoRequestDTO.getTipo());
        tipoServicoRepository.save(tipoServico);

        return modelMapper.map(tipoServico, TipoServicoDTO.class);
    }

    public TipoServicoDTO updateTipoServico(Integer id, TipoServicoDTO tipoServicoRequestDTO) {
        Optional<TipoServico> optionalTipoServico = tipoServicoRepository.findById(id);

        if (optionalTipoServico.isPresent()) {
            TipoServico tipoServico = optionalTipoServico.get();
            tipoServico.setPreco(tipoServicoRequestDTO.getPreco());
            tipoServico.setTipo(tipoServicoRequestDTO.getTipo());
            tipoServicoRepository.save(tipoServico);

            return modelMapper.map(tipoServico, TipoServicoDTO.class);
        }

        return null;
    }

    public void deleteTipoServico(Integer id) {
        tipoServicoRepository.deleteById(id);
    }
}
