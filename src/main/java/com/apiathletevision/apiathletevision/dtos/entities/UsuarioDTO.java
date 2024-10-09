package com.apiathletevision.apiathletevision.dtos.entities;

import com.apiathletevision.apiathletevision.dtos.groups.AppGroup;
import com.apiathletevision.apiathletevision.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.UUID;

public class UsuarioDTO {

    @JsonView({AppGroup.Response.class, AppGroup.ResponsePage.class})
    private UUID id;

    @JsonView({AppGroup.Response.class, AppGroup.ResponsePage.class, AppGroup.Request.class})
    private String nome;

    @JsonView({AppGroup.Response.class, AppGroup.ResponsePage.class, AppGroup.Request.class})
    private UserRole role;

    @JsonView({AppGroup.Response.class, AppGroup.ResponsePage.class, AppGroup.Request.class})
    private String email;

    @JsonView({AppGroup.Response.class, AppGroup.ResponsePage.class, AppGroup.Request.class})
    private String password;

    @JsonView({AppGroup.Response.class, AppGroup.ResponsePage.class, AppGroup.Request.class})
    private String rg;

    @JsonView({AppGroup.Response.class, AppGroup.ResponsePage.class, AppGroup.Request.class})
    private String cpf;
}
