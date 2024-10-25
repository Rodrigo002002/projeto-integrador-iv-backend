package com.apiathletevision.apiathletevision.services.impl;

import com.apiathletevision.apiathletevision.dtos.response.AuthenticatedUserDTO;
import com.apiathletevision.apiathletevision.entities.Usuario;
import com.apiathletevision.apiathletevision.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    @Autowired
    private AuthenticationService authenticationService;

    private AuthenticatedUserDTO authenticatedUserDTO;
    private Usuario authenticatedUser;

    /**
     * Retorna o usuário autenticado atual (entidade completa).
     *
     * @return Usuario autenticado
     */
    public Usuario getAuthenticatedUser() {
        load();
        return authenticatedUser;
    }

    /**
     * Retorna o DTO com as informações principais do usuário autenticado.
     *
     * @return AuthenticatedUserDTO com as informações do usuário autenticado
     */
    public AuthenticatedUserDTO getAuthenticatedUserDTO() {
        load();
        return authenticatedUserDTO;
    }

    /**
     * Carrega as informações do usuário autenticado usando o AuthenticationService.
     */
    private void load() {
        // Carrega o usuário autenticado usando o AuthenticationService
        authenticatedUserDTO = authenticationService.getCurrentUserDTO();
        authenticatedUser = authenticationService.getCurrentUser();
    }
}