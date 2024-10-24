package com.apiathletevision.apiathletevision.controllers;

import com.apiathletevision.apiathletevision.dtos.groups.AppGroup;
import com.apiathletevision.apiathletevision.dtos.jwt.JwtDTO;
import com.apiathletevision.apiathletevision.dtos.jwt.JwtRefreshToken;
import com.apiathletevision.apiathletevision.dtos.request.LoginRequestDTO;
import com.apiathletevision.apiathletevision.services.AuthenticationService;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Login")
@RequestMapping("${api-prefix}/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final AuthenticationService authenticationService;

    @JsonView(AppGroup.Response.class)
    @PostMapping("/login")
    public JwtDTO auth(
            @RequestBody
            @Validated(AppGroup.Request.class)
            LoginRequestDTO loginRequestDTO) {

        var usuarioAutenticationToken = new UsernamePasswordAuthenticationToken(
                loginRequestDTO.getLogin(), loginRequestDTO.getPassword());

        authenticationManager.authenticate(usuarioAutenticationToken);

        return authenticationService.getAccessToken(loginRequestDTO);
    }

    @PostMapping("/refresh-token")
    public JwtDTO authRefreshToken(@RequestBody JwtRefreshToken jwtRefreshToken) {
        return authenticationService.getRefreshToken(jwtRefreshToken.getRefreshToken());
    }

}