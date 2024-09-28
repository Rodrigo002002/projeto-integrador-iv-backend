package com.apiathletevision.apiathletevision.dtos;

import com.apiathletevision.apiathletevision.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ProfessorDTO {
    private UUID id;
    private String nome;
    private UserRole role;
    private String telefone;
    private String email;
    private String rg;
    private String cpf;
    private List<Integer> documentosIds;
    private List<Integer> servicoId;
}