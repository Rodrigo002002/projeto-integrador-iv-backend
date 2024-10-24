package com.apiathletevision.apiathletevision.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "alunos")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Aluno extends Usuario {

    @ManyToOne
    @JoinColumn(name = "plano_id")
    private Plano plano;

    @OneToMany
    private List<Documento> documentos;
}