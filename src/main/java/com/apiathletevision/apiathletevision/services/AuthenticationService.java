package com.apiathletevision.apiathletevision.services;

import com.apiathletevision.apiathletevision.dtos.entities.UsuarioDTO;
import com.apiathletevision.apiathletevision.dtos.jwt.JwtDTO;
import com.apiathletevision.apiathletevision.dtos.request.LoginRequestDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthenticationService extends UserDetailsService {
    JwtDTO getAccessToken(LoginRequestDTO loginRequestDTO);
    String validateToken(String token);
    JwtDTO getRefreshToken(String s);
    UsuarioDTO getAuthenticatedUser();
    void loadAuthenticatedUser();
}

