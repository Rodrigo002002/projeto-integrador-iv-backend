package com.apiathletevision.apiathletevision.dtos.entities;

import com.apiathletevision.apiathletevision.dtos.groups.AppGroup;
import com.apiathletevision.apiathletevision.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ProfessorDTO {
    @JsonView({AppGroup.Response.class, AppGroup.ResponsePage.class})
    private UUID id;

    @JsonView({AppGroup.Response.class, AppGroup.ResponsePage.class, AppGroup.Request.class})
    private String nome;

    @JsonView({AppGroup.Response.class, AppGroup.ResponsePage.class, AppGroup.Request.class})
    private UserRole role;

    @JsonView({AppGroup.Response.class, AppGroup.ResponsePage.class, AppGroup.Request.class})
    private String telefone;

    @JsonView({AppGroup.Response.class, AppGroup.ResponsePage.class, AppGroup.Request.class})
    private String email;

    @JsonView({AppGroup.Response.class, AppGroup.ResponsePage.class, AppGroup.Request.class})
    private String rg;

    @JsonView({AppGroup.Response.class, AppGroup.ResponsePage.class, AppGroup.Request.class})
    private String cpf;

    @JsonView({AppGroup.Response.class, AppGroup.ResponsePage.class, AppGroup.Request.class})
    private List<Integer> documentosIds;

    @JsonView({AppGroup.Response.class, AppGroup.ResponsePage.class, AppGroup.Request.class})
    private List<Integer> servicoId;
}