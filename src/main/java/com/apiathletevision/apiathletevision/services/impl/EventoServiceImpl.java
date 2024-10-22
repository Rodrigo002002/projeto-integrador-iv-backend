package com.apiathletevision.apiathletevision.services.impl;

import com.apiathletevision.apiathletevision.dtos.entities.EventoDTO;
import com.apiathletevision.apiathletevision.dtos.response.PageDTO;
import com.apiathletevision.apiathletevision.entities.Equipe;
import com.apiathletevision.apiathletevision.entities.Evento;
import com.apiathletevision.apiathletevision.entities.Modalidade;
import com.apiathletevision.apiathletevision.exeptions.BadRequestException;
import com.apiathletevision.apiathletevision.mappers.EventoMapper;
import com.apiathletevision.apiathletevision.repositories.EquipeRepository;
import com.apiathletevision.apiathletevision.repositories.EventoRepository;
import com.apiathletevision.apiathletevision.repositories.ModalidadeRepository;
import com.apiathletevision.apiathletevision.services.EventoService;
import com.apiathletevision.apiathletevision.services.specifications.EventoSpecification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventoServiceImpl implements EventoService {

    private final EventoRepository eventoRepository;
    private final EventoMapper eventoMapper;
    private final EquipeRepository equipeRepository;
    private final ModalidadeRepository modalidadeRepository;

    @Override
    public PageDTO<Evento, EventoDTO> findAll(int pageNo, int pageSize, String search, Boolean status) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("nome"));
        Specification<Evento> specification = EventoSpecification.getFilters(search, status);
        Page<Evento> eventoPage = eventoRepository.findAll(specification, pageable);
        return new PageDTO<>(eventoPage, eventoMapper::toDto);
    }

    @Override
    @Transactional
    public EventoDTO create(EventoDTO eventoDTO) {
        Evento evento = eventoMapper.toEntity(eventoDTO);

        setCreateAssociations(eventoDTO, evento);

        evento = eventoRepository.save(evento);
        return eventoMapper.toDto(evento);
    }

    @Override
    public EventoDTO update(EventoDTO eventoDTO) throws BadRequestException {
        Evento evento = getEvento(eventoDTO.getId());

        eventoMapper.partialUpdate(eventoDTO, evento);
        setCreateAssociations(eventoDTO, evento);

        evento = eventoRepository.save(evento);
        return eventoMapper.toDto(evento);
    }

    @Override
    public EventoDTO findById(int id) throws BadRequestException {
        Evento evento = getEvento(id);
        return eventoMapper.toDto(evento);
    }

    @Override
    public void delete(int id) throws BadRequestException {
        Evento evento = getEvento(id);
        if (!evento.getStatus()) {
            eventoRepository.delete(evento);
        }
        throw new BadRequestException("Gestor ainda ativo");
    }

    @Override
    public void changeStatus(int id, boolean status) {
        Optional<Evento> optionalEvento = eventoRepository.findById(id);
        if (optionalEvento.isPresent()) {
            Evento evento = optionalEvento.get();
            evento.setStatus(status);
            eventoRepository.save(evento);
        }
    }

    private Evento getEvento(int id) throws BadRequestException {
        return eventoRepository.findById(id).orElseThrow(() ->
                new BadRequestException("Evento com o ID: " + id + " não encontrado"));
    }

    private void setCreateAssociations(EventoDTO eventoDTO, Evento evento) {
        if (eventoDTO.getEquipesIds() != null) {
            List<Equipe> equipes = equipeRepository.findAllById(eventoDTO.getEquipesIds());
            evento.setEquipes(equipes);
        }

        if (eventoDTO.getModalidade() != null) {
            Optional<Modalidade> modalidade = modalidadeRepository.findById(eventoDTO.getModalidadeId());
            evento.setModalidade(modalidade.orElse(null));
        }
    }
}