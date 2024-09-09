package com.apiathletevision.apiathletevision.entities;

import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "responsaveis")
public class Responsavel extends Usuario {
    @ManyToMany
    private List<Aluno> alunos;
}