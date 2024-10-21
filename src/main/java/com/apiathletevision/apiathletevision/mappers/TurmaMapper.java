package com.apiathletevision.apiathletevision.mappers;

import com.apiathletevision.apiathletevision.dtos.entities.TurmaDTO;
import com.apiathletevision.apiathletevision.entities.Turma;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TurmaMapper {
    // Converte de DTO para Entidade
    Turma toEntity(TurmaDTO dto);

    // Converte de Entidade para DTO
    TurmaDTO toDto(Turma entity);

    // Converte lista de Entidades para lista de DTOs
    List<TurmaDTO> toDto(List<Turma> entities);

    // Atualiza parcialmente uma entidade com os valores do DTO, ignorando propriedades nulas
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Turma partialUpdate(TurmaDTO dto, @MappingTarget Turma entity);
}
