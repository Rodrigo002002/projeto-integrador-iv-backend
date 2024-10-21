package com.apiathletevision.apiathletevision.mappers;

import com.apiathletevision.apiathletevision.dtos.entities.EquipeDTO;
import com.apiathletevision.apiathletevision.entities.Equipe;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface EquipeMapper {
    // Converte de DTO para Entidade
    Equipe toEntity(EquipeDTO dto);

    // Converte de Entidade para DTO
    EquipeDTO toDto(Equipe entity);

    // Converte lista de Entidades para lista de DTOs
    List<EquipeDTO> toDto(List<Equipe> entities);

    // Atualiza parcialmente uma entidade com os valores do DTO, ignorando propriedades nulas
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Equipe partialUpdate(EquipeDTO dto, @MappingTarget Equipe entity);
}
