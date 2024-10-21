package com.apiathletevision.apiathletevision.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Table(name = "alunos")
@Entity
@Getter
@Setter
public class Aluno extends Usuario {

    @ManyToOne
    @JoinColumn(name = "turma_id", referencedColumnName = "id")
    private Turma turma;

    @ManyToOne
    @JoinColumn(name = "plano_id", referencedColumnName = "id")
    private Plano plano;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "documento_id", referencedColumnName = "id")
    private List<Documento> documentos;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pagamento_id", referencedColumnName = "id")
    private List<Pagamento> pagamentos;
}