package com.apiathletevision.apiathletevision.entities;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "responsaveis")
public class Responsavel extends Usuario {
    @ElementCollection
    private List<String> funcionalidades;

    @ManyToMany
    private List<Aluno> alunos;
}