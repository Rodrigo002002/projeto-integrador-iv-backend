package com.apiathletevision.apiathletevision.dtos.entities;

import com.apiathletevision.apiathletevision.dtos.groups.AppGroup;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class TurmaDTO {

    @JsonView({AppGroup.Response.class, AppGroup.ResponsePage.class})
    private Integer id;

    @JsonView({AppGroup.Response.class, AppGroup.ResponsePage.class})
    private ModalidadeDTO modalidade;

    @JsonView({AppGroup.Request.class})
    private Integer modalidadeId;

    @JsonView({AppGroup.Response.class, AppGroup.ResponsePage.class})
    private List<AulaDTO> aulas;

    @JsonView({AppGroup.Request.class})
    private List<Integer> aulasIds;

    @JsonView({AppGroup.Response.class, AppGroup.ResponsePage.class})
    private List<AlunoDTO> alunos;

    @JsonView({AppGroup.Request.class})
    private List<UUID> alunosIds;

    @JsonView({AppGroup.Response.class, AppGroup.ResponsePage.class})
    private ProfessorDTO professor;

    @JsonView({AppGroup.Request.class})
    private UUID professorId;

    @JsonView({AppGroup.Response.class, AppGroup.ResponsePage.class})
    private String periodo;
}