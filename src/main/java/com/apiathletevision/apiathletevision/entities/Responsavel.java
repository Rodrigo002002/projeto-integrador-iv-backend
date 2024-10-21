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
    @JoinTable(
            name = "responsavel_aluno_dependente",
            joinColumns = @JoinColumn(name = "responsavel_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "aluno_id", referencedColumnName = "id"))
    private List<Aluno> alunos;
}