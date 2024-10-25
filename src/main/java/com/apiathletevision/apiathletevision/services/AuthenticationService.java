package com.apiathletevision.apiathletevision.services;

import com.apiathletevision.apiathletevision.dtos.entities.UsuarioDTO;
import com.apiathletevision.apiathletevision.dtos.jwt.JwtDTO;
import com.apiathletevision.apiathletevision.dtos.request.LoginRequestDTO;
import com.apiathletevision.apiathletevision.dtos.response.AuthenticatedUserDTO;
import com.apiathletevision.apiathletevision.entities.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthenticationService extends UserDetailsService {
    AuthenticatedUserDTO getCurrentUserDTO();
    Usuario getCurrentUser();
    JwtDTO getAccessToken(LoginRequestDTO loginRequestDTO);
    String validateToken(String token);
    JwtDTO getRefreshToken(String s);
    UsuarioDTO getAuthenticatedUser();
    void loadAuthenticatedUser();
}
