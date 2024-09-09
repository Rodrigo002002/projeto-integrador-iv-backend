package com.apiathletevision.apiathletevision.services;

import com.apiathletevision.apiathletevision.dtos.EventoDTO;
import com.apiathletevision.apiathletevision.entities.Evento;
import com.apiathletevision.apiathletevision.entities.Equipe;
import com.apiathletevision.apiathletevision.entities.Modalidade;
import com.apiathletevision.apiathletevision.repositories.EventoRepository;
import com.apiathletevision.apiathletevision.repositories.EquipeRepository;
import com.apiathletevision.apiathletevision.repositories.ModalidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private EquipeRepository equipeRepository;

    @Autowired
    private ModalidadeRepository modalidadeRepository;

    public List<Evento> getAllEventos() {
        return eventoRepository.findAll();
    }

    public Optional<Evento> getEventoById(Integer id) {
        return eventoRepository.findById(id);
    }

    public Evento createEvento(EventoDTO eventoDTO) {
        Evento evento = new Evento();
        evento.setDescricao(eventoDTO.getDescricao());
        evento.setNome(eventoDTO.getNome());
        evento.setData(eventoDTO.getData());

        List<Equipe> equipes = equipeRepository.findAllById(eventoDTO.getEquipesIds());
        evento.setEquipes(equipes);

        Modalidade modalidade = modalidadeRepository.findById(eventoDTO.getModalidadeId()).orElse(null);
        evento.setModalidade(modalidade);

        return eventoRepository.save(evento);
    }

    public Evento updateEvento(Integer id, EventoDTO eventoDTO) {
        Optional<Evento> optionalEvento = eventoRepository.findById(id);
        if (optionalEvento.isPresent()) {
            Evento evento = optionalEvento.get();
            evento.setDescricao(eventoDTO.getDescricao());
            evento.setNome(eventoDTO.getNome());
            evento.setData(eventoDTO.getData());

            List<Equipe> equipes = equipeRepository.findAllById(eventoDTO.getEquipesIds());
            evento.setEquipes(equipes);

            Modalidade modalidade = modalidadeRepository.findById(eventoDTO.getModalidadeId()).orElse(null);
            evento.setModalidade(modalidade);

            return eventoRepository.save(evento);
        }
        return null;
    }

    public void deleteEvento(Integer id) {
        eventoRepository.deleteById(id);
    }
}