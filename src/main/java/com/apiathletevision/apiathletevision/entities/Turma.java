package com.apiathletevision.apiathletevision.entities;

import jakarta.persistence.CascadeType;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "turmas")
public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany
    private List<Modalidade> modalidades;
    
    @OneToMany(mappedBy = "turma", targetEntity = Aula.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Aula> aulas;
    
    @OneToMany(mappedBy = "turma", targetEntity = Aluno.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Aluno> alunos;

    @ManyToOne
    private Professor professor;

    private LocalDateTime horario;
}