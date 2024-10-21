package com.apiathletevision.apiathletevision.mappers;

import com.apiathletevision.apiathletevision.dtos.entities.ResponsavelDTO;
import com.apiathletevision.apiathletevision.entities.Responsavel;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ResponsavelMapper {
    // Converte de DTO para Entidade
    Responsavel toEntity(ResponsavelDTO dto);

    // Converte de Entidade para DTO
    ResponsavelDTO toDto(Responsavel entity);

    // Converte lista de Entidades para lista de DTOs
    List<ResponsavelDTO> toDto(List<Responsavel> entities);

    // Atualiza parcialmente uma entidade com os valores do DTO, ignorando propriedades nulas
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Responsavel partialUpdate(ResponsavelDTO dto, @MappingTarget Responsavel entity);
}
