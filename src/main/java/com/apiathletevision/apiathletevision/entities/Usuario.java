package com.apiathletevision.apiathletevision.entities;

import com.apiathletevision.apiathletevision.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
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

    @Column(name = "login", unique = true, nullable = false)
    private String login;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "role", nullable = false)
    private UserRole role;

    private String telefone;

    @Column(name = "email", unique = true, nullable = false, insertable = false, updatable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "rg", nullable = false)
    private String rg;

    @Column(name = "cpf", nullable = false)
    private String cpf;

    @Column(name = "status", nullable = false)
    @ColumnDefault("true")
    private Boolean status;

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