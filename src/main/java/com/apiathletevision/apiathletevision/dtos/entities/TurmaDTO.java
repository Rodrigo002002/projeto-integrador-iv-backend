package com.apiathletevision.apiathletevision.dtos.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class TurmaDTO {
    private Integer id;
    private Integer modalidadeId;
    private List<Integer> aulaIds;
    private List<UUID> alunoIds;
    private UUID professorId;
    private String periodo;
}