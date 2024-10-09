package com.apiathletevision.apiathletevision.dtos.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class EquipeDTO {
    private Integer id;
    private String nome;
    private List<UUID> alunosIds;
}