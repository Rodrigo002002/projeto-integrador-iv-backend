package com.apiathletevision.apiathletevision.services;

import com.apiathletevision.apiathletevision.dtos.EventoDTO;
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
        EventoDTO eventoDTO = modelMapper.map(evento, EventoDTO.class);
        return Optional.ofNullable(eventoDTO);
    }

    public EventoDTO createEvento(EventoDTO eventoDTO) {
        Evento evento = new Evento();
        evento.setDescricao(eventoDTO.getDescricao());
        evento.setNome(eventoDTO.getNome());
        evento.setData(eventoDTO.getData());
        List<Equipe> equipes = equipeRepository.findAllById(eventoDTO.getEquipesIds());
        evento.setEquipes(equipes);
        Modalidade modalidade = modalidadeRepository.findById(eventoDTO.getModalidadeId()).orElse(null);
        evento.setModalidade(modalidade);

        evento = eventoRepository.save(evento);

        return modelMapper.map(evento, EventoDTO.class);
    }

    public EventoDTO updateEvento(Integer id, EventoDTO eventoDTO) {
        Optional<Evento> optionalEvento = eventoRepository.findById(id);

        if (optionalEvento.isPresent()) {

            Evento evento = optionalEvento.get();
            evento.setId(id);
            evento.setDescricao(eventoDTO.getDescricao());
            evento.setNome(eventoDTO.getNome());
            evento.setData(eventoDTO.getData());
            List<Equipe> equipes = equipeRepository.findAllById(eventoDTO.getEquipesIds());
            evento.setEquipes(equipes);
            Modalidade modalidade = modalidadeRepository.findById(eventoDTO.getModalidadeId()).orElse(null);
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