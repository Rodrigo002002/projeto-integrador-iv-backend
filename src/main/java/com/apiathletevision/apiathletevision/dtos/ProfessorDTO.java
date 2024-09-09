package com.apiathletevision.apiathletevision.dtos;

import com.apiathletevision.apiathletevision.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfessorDTO {
    private String nome;
    private UserRole role;
    private String telefone;
    private String email;
    private Integer servicoId;
}