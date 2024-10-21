package com.apiathletevision.apiathletevision.mappers;

import com.apiathletevision.apiathletevision.dtos.entities.TipoServicoDTO;
import com.apiathletevision.apiathletevision.entities.TipoServico;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TipoServicoMapper {
    // Converte de DTO para Entidade
    TipoServico toEntity(TipoServicoDTO dto);

    // Converte de Entidade para DTO
    TipoServicoDTO toDto(TipoServico entity);

    // Converte lista de Entidades para lista de DTOs
    List<TipoServicoDTO> toDtoList(List<TipoServico> entities);

    // Converte lista de Entidades para lista de DTOs
    List<TipoServico> toEntityList(List<TipoServicoDTO> dtos);

    // Atualiza parcialmente uma entidade com os valores do DTO, ignorando propriedades nulas
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TipoServico partialUpdate(TipoServicoDTO dto, @MappingTarget TipoServico entity);
}
