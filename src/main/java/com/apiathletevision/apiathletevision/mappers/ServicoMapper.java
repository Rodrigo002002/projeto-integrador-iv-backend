package com.apiathletevision.apiathletevision.mappers;

import com.apiathletevision.apiathletevision.dtos.entities.ServicoDTO;
import com.apiathletevision.apiathletevision.entities.Servico;
import com.apiathletevision.apiathletevision.entities.TipoServico;
import com.apiathletevision.apiathletevision.repositories.TipoServicoRepository;
import org.mapstruct.*;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ServicoMapper {

    // Converte de DTO para Entidade
    Servico toEntity(ServicoDTO dto);

    // Converte de Entidade para DTO
    ServicoDTO toDto(Servico entity);

    // Converte lista de Entidades para lista de DTOs
    List<ServicoDTO> toDto(List<Servico> entities);

    // Atualiza parcialmente uma entidade com os valores do DTO, ignorando propriedades nulas
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Servico partialUpdate(ServicoDTO dto, @MappingTarget Servico entity);

    // Mapeamento customizado de Integer para TipoServico
    default TipoServico map(Integer value) {
        TipoServicoRepository tipoServicoRepository = null;

        if (value == null) {
            return null;
        }
        return tipoServicoRepository.findById(value)
                .orElseThrow(() -> new IllegalArgumentException("TipoServico não encontrado para o ID: " + value));
    }

    // Mapeamento de TipoServico para Integer (caso necessário no DTO)
    default Integer map(TipoServico tipoServico) {
        if (tipoServico == null) {
            return null;
        }
        return tipoServico.getId(); // Supondo que TipoServico tenha um método getId()
    }
}
