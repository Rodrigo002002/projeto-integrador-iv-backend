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
    @JoinColumn(name = "modalidade_id", referencedColumnName = "id")
    private Modalidade modalidade;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "aula_id", referencedColumnName = "id")
    private List<Aula> aulas;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "aluno_id", referencedColumnName = "id")
    private List<Aluno> alunos;

    @ManyToOne
    @JoinColumn(name = "professor_id", referencedColumnName = "id")
    private Professor professor;

    @Column(name = "periodo")
    private String periodo;
}