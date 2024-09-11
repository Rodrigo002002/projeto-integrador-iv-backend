package com.apiathletevision.apiathletevision.dtos.auth;

import java.util.List;

public record RegisterAlunoDTO(String nome,
                               String telefone,
                               String email,
                               String password,
                               List<Integer> documentosIds) {
}