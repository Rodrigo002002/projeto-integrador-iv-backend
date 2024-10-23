package com.apiathletevision.apiathletevision.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Table(name = "alunos")
@Entity
@Getter
@Setter
public class Aluno extends Usuario {

    @ManyToOne
    private Turma turma;

    @ManyToOne
    private Plano plano;

    @OneToMany
    private List<Servico> servicos;

    @OneToMany
    private List<Documento> documentos;

    @OneToMany
    private List<Pagamento> pagamentos;
}