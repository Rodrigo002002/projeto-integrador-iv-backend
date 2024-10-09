package com.apiathletevision.apiathletevision.services.impl;

import com.apiathletevision.apiathletevision.dtos.entities.EventoDTO;
import com.apiathletevision.apiathletevision.entities.Equipe;
import com.apiathletevision.apiathletevision.entities.Evento;
import com.apiathletevision.apiathletevision.entities.Modalidade;
import com.apiathletevision.apiathletevision.repositories.EquipeRepository;
import com.apiathletevision.apiathletevision.repositories.EventoRepository;
import com.apiathletevision.apiathletevision.repositories.ModalidadeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventoService {

    private final EventoRepository eventoRepository;

    private final EquipeRepository equipeRepository;

    private final ModalidadeRepository modalidadeRepository;

    private final ModelMapper modelMapper;

    public List<EventoDTO> getAllEventos() {
        return eventoRepository.findAll().stream().map(evento -> modelMapper.map(evento, EventoDTO.class)).toList();
    }

    public Optional<EventoDTO> getEventoById(Integer id) {
        Optional<Evento> evento = eventoRepository.findById(id);
        EventoDTO eventoRequestDTO = modelMapper.map(evento, EventoDTO.class);
        return Optional.ofNullable(eventoRequestDTO);
    }

    public EventoDTO createEvento(EventoDTO eventoRequestDTO) {
        Evento evento = new Evento();
        evento.setDescricao(eventoRequestDTO.getDescricao());
        evento.setNome(eventoRequestDTO.getNome());
        evento.setData(eventoRequestDTO.getData());
        List<Equipe> equipes = equipeRepository.findAllById(eventoRequestDTO.getEquipesIds());
        evento.setEquipes(equipes);
        Modalidade modalidade = modalidadeRepository.findById(eventoRequestDTO.getModalidadeId()).orElse(null);
        evento.setModalidade(modalidade);

        evento = eventoRepository.save(evento);

        return modelMapper.map(evento, EventoDTO.class);
    }

    public EventoDTO updateEvento(Integer id, EventoDTO eventoRequestDTO) {
        Optional<Evento> optionalEvento = eventoRepository.findById(id);

        if (optionalEvento.isPresent()) {

            Evento evento = optionalEvento.get();
            evento.setId(id);
            evento.setDescricao(eventoRequestDTO.getDescricao());
            evento.setNome(eventoRequestDTO.getNome());
            evento.setData(eventoRequestDTO.getData());
            List<Equipe> equipes = equipeRepository.findAllById(eventoRequestDTO.getEquipesIds());
            evento.setEquipes(equipes);
            Modalidade modalidade = modalidadeRepository.findById(eventoRequestDTO.getModalidadeId()).orElse(null);
            evento.setModalidade(modalidade);

            evento = eventoRepository.save(evento);

            return modelMapper.map(evento, EventoDTO.class);
        }
        return null;
    }

    public void deleteEvento(Integer id) {
        eventoRepository.deleteById(id);
    }
}