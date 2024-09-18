package com.apiathletevision.apiathletevision.dtos;

import com.apiathletevision.apiathletevision.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GestorDTO {
    private String nome;
    private UserRole role;
    private String telefone;
    private String rg;
    private String cpf;
    private String email;
}