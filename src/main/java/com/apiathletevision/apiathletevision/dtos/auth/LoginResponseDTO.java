package com.apiathletevision.apiathletevision.dtos.auth;

import com.apiathletevision.apiathletevision.enums.UserRole;

public record LoginResponseDTO(String token, String nome, String email, UserRole role) {
}