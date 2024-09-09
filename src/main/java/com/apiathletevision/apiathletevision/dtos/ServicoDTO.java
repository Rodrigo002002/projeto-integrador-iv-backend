package com.apiathletevision.apiathletevision.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ServicoDTO {
    private Integer id;
    private String tipo;
    private Integer pagamentoId;
    private UUID professorId;
    private UUID alunoId;
}