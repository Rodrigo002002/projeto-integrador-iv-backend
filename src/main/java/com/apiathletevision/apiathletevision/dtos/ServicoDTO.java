package com.apiathletevision.apiathletevision.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ServicoDTO {
    private Integer id;
    private Integer tipoServico;
    private List<Integer> pagamentoIds;
    private UUID professorId;
    private UUID alunoId;
}