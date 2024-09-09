package com.apiathletevision.apiathletevision.dtos;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class AulaDTO {
    private Integer id;
    private Date data;
    private List<UUID> alunosPresentesIds;
    private Integer turmaId;
}