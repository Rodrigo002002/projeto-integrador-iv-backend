package com.apiathletevision.apiathletevision.mappers;

import com.apiathletevision.apiathletevision.dtos.entities.DocumentoDTO;
import com.apiathletevision.apiathletevision.entities.Documento;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface DocumentoMapper {
    // Converte de DTO para Entidade
    Documento toEntity(DocumentoDTO dto);

    // Converte de Entidade para DTO
    DocumentoDTO toDto(Documento entity);

    // Converte lista de Entidades para lista de DTOs
    List<DocumentoDTO> toDtoList(List<Documento> entities);

    // Converte lista de Entidades para lista de DTOs
    List<Documento> toEntityList(List<DocumentoDTO> dtos);

    // Atualiza parcialmente uma entidade com os valores do DTO, ignorando propriedades nulas
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Documento partialUpdate(DocumentoDTO dto, @MappingTarget Documento entity);
}
