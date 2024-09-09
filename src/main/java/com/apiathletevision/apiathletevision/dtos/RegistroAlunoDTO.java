package com.apiathletevision.apiathletevision.dtos;

import java.util.List;

public record RegistroAlunoDTO(String nome,
                               String telefone,
                               String email,
                               String password,
                               List<Integer> documentosIds) {
}