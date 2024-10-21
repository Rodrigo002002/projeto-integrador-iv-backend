package com.apiathletevision.apiathletevision.mappers;

import com.apiathletevision.apiathletevision.dtos.entities.ProfessorDTO;
import com.apiathletevision.apiathletevision.entities.Professor;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProfessorMapper {
    // Converte de DTO para Entidade
    Professor toEntity(ProfessorDTO dto);

    // Converte de Entidade para DTO
    ProfessorDTO toDto(Professor entity);

    // Converte lista de Entidades para lista de DTOs
    List<ProfessorDTO> toDto(List<Professor> entities);

    // Atualiza parcialmente uma entidade com os valores do DTO, ignorando propriedades nulas
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Professor partialUpdate(ProfessorDTO dto, @MappingTarget Professor entity);
}
