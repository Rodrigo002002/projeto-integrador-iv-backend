package com.apiathletevision.apiathletevision.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class TurmaDTO {
    private Integer id;
    private List<Integer> modalidadeIds;
    private List<Integer> aulaIds;
    private List<UUID> alunoIds;
    private UUID professorId;
    private LocalDateTime horario;
}