package com.apiathletevision.apiathletevision.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "turmas")
public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    private Modalidade modalidade;

    @OneToMany(mappedBy = "turma", targetEntity = Aula.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Aula> aulas;

    @OneToMany(mappedBy = "turma", targetEntity = Aluno.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Aluno> alunos;

    @ManyToOne
    private Professor professor;

    @Column(name = "periodo")
    private String periodo;
}