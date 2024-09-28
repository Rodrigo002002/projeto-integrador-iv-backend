package com.apiathletevision.apiathletevision.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "alunos")
public class Aluno extends Usuario {

    @ManyToOne(cascade = CascadeType.ALL)
    private Turma turma;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Documento> documentos;

    @ManyToOne(cascade = CascadeType.ALL)
    private Plano plano;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Pagamento> pagamentos;
}