package com.apiathletevision.apiathletevision.mappers;

import com.apiathletevision.apiathletevision.dtos.entities.PagamentoDTO;
import com.apiathletevision.apiathletevision.entities.Pagamento;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PagamentoMapper {
    // Converte de DTO para Entidade
    Pagamento toEntity(PagamentoDTO dto);

    // Converte de Entidade para DTO
    PagamentoDTO toDto(Pagamento entity);

    // Converte lista de Entidades para lista de DTOs
    List<PagamentoDTO> toDtoList(List<Pagamento> entities);

    // Converte lista de Entidades para lista de DTOs
    List<Pagamento> toEntityList(List<PagamentoDTO> dtos);

    // Atualiza parcialmente uma entidade com os valores do DTO, ignorando propriedades nulas
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Pagamento partialUpdate(PagamentoDTO dto, @MappingTarget Pagamento entity);
}
