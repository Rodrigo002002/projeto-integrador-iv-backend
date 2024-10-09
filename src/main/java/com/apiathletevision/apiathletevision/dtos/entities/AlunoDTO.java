package com.apiathletevision.apiathletevision.dtos.entities;

import com.apiathletevision.apiathletevision.dtos.groups.AppGroup;
import com.apiathletevision.apiathletevision.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class AlunoDTO {

    @JsonView({AppGroup.Response.class, AppGroup.ResponsePage.class})
    private UUID id;

    @Schema(defaultValue = "João da Silva")
    @JsonView({AppGroup.Response.class, AppGroup.ResponsePage.class, AppGroup.Request.class})
    private String nome;

    @Schema(defaultValue = "joãodasilva123")
    @JsonView({AppGroup.Response.class, AppGroup.ResponsePage.class, AppGroup.Request.class})
    private String password;

    @Schema(defaultValue = "(41) 98726-5362")
    @JsonView({AppGroup.Response.class, AppGroup.ResponsePage.class, AppGroup.Request.class})
    private String telefone;

    @Schema(defaultValue = "joaodasilva@gmail.com")
    @JsonView({AppGroup.Response.class, AppGroup.ResponsePage.class, AppGroup.Request.class})
    private String email;

    @Schema(defaultValue = "192.837.482-9")
    @JsonView({AppGroup.Response.class, AppGroup.ResponsePage.class, AppGroup.Request.class})
    private String rg;

    @Schema(defaultValue = "198.827.837.29")
    @JsonView({AppGroup.Response.class, AppGroup.ResponsePage.class, AppGroup.Request.class})
    private String cpf;

    @JsonView({AppGroup.Response.class, AppGroup.ResponsePage.class, AppGroup.Request.class})
    private UserRole role;

    @JsonView({AppGroup.Response.class, AppGroup.ResponsePage.class, AppGroup.Request.class})
    private Integer turmaId;

    @JsonView({AppGroup.Response.class, AppGroup.ResponsePage.class, AppGroup.Request.class})
    private List<Integer> documentosIds;

    @JsonView({AppGroup.Response.class, AppGroup.ResponsePage.class, AppGroup.Request.class})
    private Integer planoId;
}