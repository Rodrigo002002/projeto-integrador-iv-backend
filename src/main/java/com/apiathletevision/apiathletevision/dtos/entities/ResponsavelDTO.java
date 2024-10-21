package com.apiathletevision.apiathletevision.dtos.entities;

import com.apiathletevision.apiathletevision.dtos.groups.AppGroup;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ResponsavelDTO extends UsuarioDTO {

    @JsonView({AppGroup.Response.class, AppGroup.ResponsePage.class})
    private List<AlunoDTO> alunos;

    @JsonView({AppGroup.Request.class})
    private List<UUID> alunosIds;
}