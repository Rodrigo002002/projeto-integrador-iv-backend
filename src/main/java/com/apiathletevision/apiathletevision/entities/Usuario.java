package com.apiathletevision.apiathletevision.entities;

import com.apiathletevision.apiathletevision.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    private String login;
    private String nome;
    private UserRole role;
    private String telefone;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    private String password;
    private String rg;
    private String cpf;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.GESTOR) return List.of(
                new SimpleGrantedAuthority("ROLE_GESTOR"),
                new SimpleGrantedAuthority("ROLE_PROFESSOR"),
                new SimpleGrantedAuthority("ROLE_ALUNO"),
                new SimpleGrantedAuthority("ROLE_RESPONSAVEL")
        );

        else return List.of(new SimpleGrantedAuthority("ROLE_ALUNO"));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }
}