package com.apiathletevision.apiathletevision.dtos;

import com.apiathletevision.apiathletevision.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ResponsavelDTO {
    private String nome;
    private UserRole role;
    private String telefone;
    private String email;
    private String password;
    private List<UUID> alunosIds;  // IDs dos alunos associados
}