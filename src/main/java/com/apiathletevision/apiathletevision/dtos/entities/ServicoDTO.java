package com.apiathletevision.apiathletevision.dtos.entities;

import com.apiathletevision.apiathletevision.dtos.groups.AppGroup;
import com.apiathletevision.apiathletevision.entities.Pagamento;
import com.apiathletevision.apiathletevision.entities.Professor;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ServicoDTO {

    @JsonView({AppGroup.Response.class, AppGroup.ResponsePage.class, AppGroup.Request.class})
    private Integer id;

    @JsonView({AppGroup.Response.class, AppGroup.ResponsePage.class})
    private TipoServicoDTO tipoServico;

    @JsonView({AppGroup.Request.class})
    private Integer tipoServicoId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonView({AppGroup.Response.class, AppGroup.ResponsePage.class, AppGroup.Request.class})
    @NotNull(message = "Preenchimento obrigatório")
    private LocalDate data;

    @JsonView({AppGroup.Response.class, AppGroup.ResponsePage.class})
    private ProfessorDTO professor;

    @JsonView({AppGroup.Request.class})
    private UUID professorId;

    @JsonView({AppGroup.Response.class, AppGroup.ResponsePage.class})
    private AlunoDTO aluno;

    @JsonView({AppGroup.Request.class})
    private UUID alunoId;

    @JsonView({AppGroup.Response.class, AppGroup.ResponsePage.class, AppGroup.Request.class})
    private Boolean status;
}