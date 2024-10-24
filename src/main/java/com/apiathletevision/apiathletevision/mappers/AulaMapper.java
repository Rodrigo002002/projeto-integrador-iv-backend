package com.apiathletevision.apiathletevision.mappers;

import com.apiathletevision.apiathletevision.dtos.entities.AulaDTO;
import com.apiathletevision.apiathletevision.entities.Aula;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AulaMapper {
    // Converte de DTO para Entidade
    Aula toEntity(AulaDTO dto);

    // Converte de Entidade para DTO
    AulaDTO toDto(Aula entity);

    // Converte lista de Entidades para lista de DTOs
    List<AulaDTO> toDtoList(List<Aula> entities);

    // Atualiza parcialmente uma entidade com os valores do DTO, ignorando propriedades nulas
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Aula partialUpdate(AulaDTO dto, @MappingTarget Aula entity);
}
