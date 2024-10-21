package com.apiathletevision.apiathletevision.mappers;

import com.apiathletevision.apiathletevision.dtos.entities.AlunoDTO;
import com.apiathletevision.apiathletevision.entities.Aluno;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AlunoMapper {
    // Converte de DTO para Entidade
    Aluno toEntity(AlunoDTO dto);

    // Converte de Entidade para DTO
    AlunoDTO toDto(Aluno entity);

    // Converte lista de Entidades para lista de DTOs
    List<AlunoDTO> toDtoList(List<Aluno> entities);

    // Atualiza parcialmente uma entidade com os valores do DTO, ignorando propriedades nulas
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Aluno partialUpdate(AlunoDTO dto, @MappingTarget Aluno entity);
}
