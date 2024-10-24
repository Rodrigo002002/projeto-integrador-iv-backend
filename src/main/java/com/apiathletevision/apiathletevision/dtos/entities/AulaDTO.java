package com.apiathletevision.apiathletevision.dtos.entities;

import com.apiathletevision.apiathletevision.dtos.groups.AppGroup;
import com.apiathletevision.apiathletevision.entities.Aluno;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class AulaDTO {

    @JsonView({AppGroup.Response.class, AppGroup.ResponsePage.class})
    private Integer id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonView({AppGroup.Response.class, AppGroup.ResponsePage.class, AppGroup.Request.class})
    @NotNull(message = "Preenchimento obrigatório")
    private LocalDate data;

    @JsonView({AppGroup.Response.class, AppGroup.ResponsePage.class})
    private List<AlunoDTO> alunosPresentes;

    @JsonView({AppGroup.Request.class})
    private List<UUID> alunosPresentesIds;

    @JsonView({AppGroup.Response.class, AppGroup.ResponsePage.class})
    private ProfessorDTO professor;

    @JsonView({AppGroup.Request.class})
    private UUID professorId;

    @JsonView({AppGroup.Response.class, AppGroup.ResponsePage.class})
    private TurmaDTO turma;

    @JsonView({AppGroup.Request.class})
    private Integer turmaId;

    @JsonView({AppGroup.Response.class, AppGroup.ResponsePage.class, AppGroup.Request.class})
    private Boolean status;
}