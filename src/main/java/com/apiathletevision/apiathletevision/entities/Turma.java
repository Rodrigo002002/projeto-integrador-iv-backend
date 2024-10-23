package com.apiathletevision.apiathletevision.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Table(name = "turmas")
@Entity
@Getter
@Setter
public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    private Modalidade modalidade;

    @OneToMany
    private List<Aula> aulas;

    @OneToMany
    private List<Aluno> alunos;

    @ManyToOne
    private Professor professor;

    private String periodo;
}