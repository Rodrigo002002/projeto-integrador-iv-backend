package com.apiathletevision.apiathletevision.mappers;

import com.apiathletevision.apiathletevision.dtos.entities.UsuarioDTO;
import com.apiathletevision.apiathletevision.entities.Usuario;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UsuarioMapper {
    // Converte de DTO para Entidade
    Usuario toEntity(UsuarioDTO dto);

    // Converte de Entidade para DTO
    UsuarioDTO toDto(Usuario entity);

    // Converte lista de Entidades para lista de DTOs
    List<UsuarioDTO> toDto(List<Usuario> entities);

    // Atualiza parcialmente uma entidade com os valores do DTO, ignorando propriedades nulas
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Usuario partialUpdate(UsuarioDTO dto, @MappingTarget Usuario entity);
}
