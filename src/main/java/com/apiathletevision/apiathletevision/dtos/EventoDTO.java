package com.apiathletevision.apiathletevision.dtos;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
public class EventoDTO {
    private Integer id;
    private String descricao;
    private String nome;
    private Date data;
    private List<Integer> equipesIds;
    private Integer modalidadeId;
}