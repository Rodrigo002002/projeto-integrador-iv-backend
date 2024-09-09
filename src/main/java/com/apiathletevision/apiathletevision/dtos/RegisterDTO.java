package com.apiathletevision.apiathletevision.dtos;

import com.apiathletevision.apiathletevision.enums.UserRole;

public record RegisterDTO(String nome, String telefone, String email, String password, UserRole role) {
}
