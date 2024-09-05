package com.apiathletevision.apiathletevision.entities;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "alunos")
public class Aluno extends Usuario {
    @ManyToOne
    private Turma turma;

    @ElementCollection
    private List<String> funcionalidades;

    @OneToMany
    private List<Documento> documentos;

    @ManyToOne
    private Plano plano;
}