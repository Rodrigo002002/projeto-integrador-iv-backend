package com.apiathletevision.apiathletevision.mappers;

import com.apiathletevision.apiathletevision.dtos.entities.ModalidadeDTO;
import com.apiathletevision.apiathletevision.entities.Modalidade;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ModalidadeMapper {
    // Converte de DTO para Entidade
    Modalidade toEntity(ModalidadeDTO dto);

    // Converte de Entidade para DTO
    ModalidadeDTO toDto(Modalidade entity);

    // Converte lista de Entidades para lista de DTOs
    List<ModalidadeDTO> toDtoList(List<Modalidade> entities);

    // Atualiza parcialmente uma entidade com os valores do DTO, ignorando propriedades nulas
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Modalidade partialUpdate(ModalidadeDTO dto, @MappingTarget Modalidade entity);
}
