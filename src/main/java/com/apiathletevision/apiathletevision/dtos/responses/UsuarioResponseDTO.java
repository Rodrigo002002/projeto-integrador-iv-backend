package com.apiathletevision.apiathletevision.dtos.responses;

import com.apiathletevision.apiathletevision.enums.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UsuarioResponseDTO {
    private UUID id;

    @Schema(defaultValue = "João da Silva")
    private String nome;

    @Schema(defaultValue = "joãodasilva123")
    private String password;

    @Schema(defaultValue = "(41) 98726-5362")
    private String telefone;

    @Schema(defaultValue = "joaodasilva@gmail.com")
    private String email;

    @Schema(defaultValue = "192.837.482-9")
    private String rg;

    @Schema(defaultValue = "198.827.837.29")
    private String cpf;

    private UserRole role;
}
