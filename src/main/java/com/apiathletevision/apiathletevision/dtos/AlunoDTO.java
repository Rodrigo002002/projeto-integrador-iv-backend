package com.apiathletevision.apiathletevision.dtos;

import com.apiathletevision.apiathletevision.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AlunoDTO {
    private String nome;
    private UserRole role;
    private String telefone;
    private String email;
    private String password;
    private Integer turmaId;
    private String rg;
    private String cpf;
    private List<Integer> documentosIds;
    private Integer planoId;
}