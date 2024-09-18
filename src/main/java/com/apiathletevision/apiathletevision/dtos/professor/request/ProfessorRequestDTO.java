package com.apiathletevision.apiathletevision.dtos.professor.request;

import com.apiathletevision.apiathletevision.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProfessorRequestDTO {
    private String nome;
    private UserRole role;
    private String telefone;
    private String email;
    private String rg;
    private String cpf;
    private List<Integer> documentosIds;
    private List<Integer> servicoId;
}