package com.apiathletevision.apiathletevision.services.impl;


import com.apiathletevision.apiathletevision.dtos.entities.UsuarioDTO;
import com.apiathletevision.apiathletevision.dtos.jwt.JwtDTO;
import com.apiathletevision.apiathletevision.dtos.request.LoginRequestDTO;
import com.apiathletevision.apiathletevision.dtos.response.AuthenticatedUserDTO;
import com.apiathletevision.apiathletevision.entities.Usuario;
import com.apiathletevision.apiathletevision.exeptions.AuthenticationException;
import com.apiathletevision.apiathletevision.exeptions.BadRequestException;
import com.apiathletevision.apiathletevision.mappers.UsuarioMapper;
import com.apiathletevision.apiathletevision.repositories.UsuarioRepository;
import com.apiathletevision.apiathletevision.services.AuthenticationService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

import static com.apiathletevision.apiathletevision.constants.Headers.ISSUER;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    @Value("${api.security.token.secret}")
    private String secretKey;

    @Value("${api.security.token.accessTokenExpiration}")
    private Integer timeExpirationToken;

    @Value("${api.security.token.refreshTokenExpiration}")
    private Integer timeExpirationRefreshToken;

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    @Getter
    private UsuarioDTO currentUserDTO = null;
    @Getter
    private Usuario currentUser = null;

    // Implementação de loadUserByUsername
    @Override
    public Usuario loadUserByUsername(String login) throws AuthenticationException {
        Usuario usuario = usuarioRepository.findByLogin(login);
        if (usuario == null) {
            throw new AuthenticationException("Usuário inexistente ou senha inválida");
        }
        return usuario;
    }

    // Implementação de getAuthenticatedUserDTO
    @Override
    public AuthenticatedUserDTO getCurrentUserDTO() {
        loadAuthenticatedUser();
        // Converte Usuario para AuthenticatedUserDTO
        if (currentUser != null) {
            return new AuthenticatedUserDTO(
                    currentUser.getId(),
                    currentUser.getLogin(),
                    currentUser.getEmail(),
                    currentUser.getRole().getRole()
            );
        }
        return null; // Retorna null ou lança uma exceção dependendo da sua lógica
    }

    @Override
    @Transactional
    public UsuarioDTO getAuthenticatedUser() {
        loadAuthenticatedUser();
        if (currentUserDTO == null) {
            throw new BadCredentialsException("Token inválido");
        }
        return currentUserDTO;
    }

    // Implementação de loadAuthenticatedUser
    @Override
    public void loadAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new BadCredentialsException("Usuário não autenticado");
        }

        String username = authentication.getName();
        Optional<Usuario> optionalUsuario = Optional.ofNullable(usuarioRepository.findByLogin(username));

        if (optionalUsuario.isPresent()) {
            currentUser = optionalUsuario.get();
            currentUserDTO = compileByEntity(currentUser); // Aqui você está convertendo para UsuarioDTO
        }
    }

    private UsuarioDTO compileByEntity(Usuario usuario) {
        return usuarioMapper.toDto(usuario);
    }

    @Override
    public JwtDTO getAccessToken(LoginRequestDTO loginRequestDTO) {
        Usuario usuario = usuarioRepository.findByLogin(loginRequestDTO.getLogin());
        if (usuario == null) {
            throw new BadRequestException("Usuário não encontrado!");
        }

        UsuarioDTO usuarioDTO = usuarioMapper.toDto(usuario);
        return JwtDTO.builder()
                .accessToken(generateTokenJwt(usuario, timeExpirationToken))
                .refreshToken(generateTokenJwt(usuario, timeExpirationRefreshToken))
                .usuario(usuarioDTO)
                .build();
    }

    public String generateTokenJwt(Usuario usuario, Integer expiration) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(generateExpirationDate(expiration))
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao tentar gerar o token! " + exception.getMessage());
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            log.info("Token inválido: {}", exception.getMessage());
            return null; // ou lance uma exceção
        }
    }

    @Override
    public JwtDTO getRefreshToken(String refreshToken) {
        String login = validateToken(refreshToken);
        Usuario usuario = usuarioRepository.findByLogin(login);
        if (usuario == null) {
            throw new BadRequestException("Usuário não encontrado!");
        }

        UsuarioDTO usuarioDTO = usuarioMapper.toDto(usuario);

        var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return JwtDTO.builder()
                .accessToken(generateTokenJwt(usuario, timeExpirationToken))
                .refreshToken(generateTokenJwt(usuario, timeExpirationRefreshToken))
                .usuario(usuarioDTO)
                .build();
    }

    private Instant generateExpirationDate(Integer expiration) {
        return LocalDateTime.now()
                .plusHours(expiration)
                .toInstant(ZoneOffset.of("-03:00"));
    }
}