package com.apiathletevision.apiathletevision.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "professores")
public class Professor extends Usuario {

    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL)
    @Column(name = "turmas")
    private List<Turma> turmas;

    @OneToMany(cascade = CascadeType.ALL)
    @Column(name = "documentos")
    private List<Documento> documentos;

    @OneToOne(cascade = CascadeType.ALL)
    @Column(name = "servicos")
    private List<Servico> servicos;
}
