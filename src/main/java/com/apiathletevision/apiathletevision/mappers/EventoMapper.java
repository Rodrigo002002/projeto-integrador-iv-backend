package com.apiathletevision.apiathletevision.mappers;

import com.apiathletevision.apiathletevision.dtos.entities.EventoDTO;
import com.apiathletevision.apiathletevision.entities.Evento;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface EventoMapper {
    // Converte de DTO para Entidade
    Evento toEntity(EventoDTO dto);

    // Converte de Entidade para DTO
    EventoDTO toDto(Evento entity);

    // Converte lista de Entidades para lista de DTOs
    List<EventoDTO> toDto(List<Evento> entities);

    // Atualiza parcialmente uma entidade com os valores do DTO, ignorando propriedades nulas
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Evento partialUpdate(EventoDTO dto, @MappingTarget Evento entity);
}
