package com.apiathletevision.apiathletevision.entities;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "professores")
public class Professor extends Usuario {
    @OneToMany(mappedBy = "professor")
    private List<Turma> turmas;

    @OneToMany
    private List<Documento> documentos;

    @ElementCollection
    private List<String> funcionalidades;

    @OneToOne
    private Servico servico;
}
