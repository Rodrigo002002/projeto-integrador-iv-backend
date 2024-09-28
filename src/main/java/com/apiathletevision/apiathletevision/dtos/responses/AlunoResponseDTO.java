package com.apiathletevision.apiathletevision.dtos.responses;

import com.apiathletevision.apiathletevision.entities.Documento;
import com.apiathletevision.apiathletevision.entities.Plano;
import com.apiathletevision.apiathletevision.entities.Turma;
import com.apiathletevision.apiathletevision.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class AlunoResponseDTO {
    private UUID id;
    private String nome;
    private UserRole role;
    private String telefone;
    private String email;
    private String password;
    private Turma turma;
    private String rg;
    private String cpf;
    private List<Documento> documentos;
    private Plano plano;
}