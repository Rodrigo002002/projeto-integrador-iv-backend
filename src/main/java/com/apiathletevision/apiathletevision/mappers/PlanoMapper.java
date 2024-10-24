package com.apiathletevision.apiathletevision.mappers;

import com.apiathletevision.apiathletevision.dtos.entities.PlanoDTO;
import com.apiathletevision.apiathletevision.entities.Plano;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PlanoMapper {
    // Converte de DTO para Entidade
    Plano toEntity(PlanoDTO dto);

    // Converte de Entidade para DTO
    PlanoDTO toDto(Plano entity);

    // Converte lista de Entidades para lista de DTOs
    List<PlanoDTO> toDtoList(List<Plano> entities);

    // Atualiza parcialmente uma entidade com os valores do DTO, ignorando propriedades nulas
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Plano partialUpdate(PlanoDTO dto, @MappingTarget Plano entity);
}
