package com.apiathletevision.apiathletevision.mappers;

import com.apiathletevision.apiathletevision.dtos.entities.GestorDTO;
import com.apiathletevision.apiathletevision.entities.Gestor;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface GestorMapper {
    // Converte de DTO para Entidade
    Gestor toEntity(GestorDTO dto);

    // Converte de Entidade para DTO
    GestorDTO toDto(Gestor entity);

    // Converte lista de Entidades para lista de DTOs
    List<GestorDTO> toDtoList(List<Gestor> entities);

    // Atualiza parcialmente uma entidade com os valores do DTO, ignorando propriedades nulas
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Gestor partialUpdate(GestorDTO dto, @MappingTarget Gestor entity);
}
