package com.apiathletevision.apiathletevision.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "servicos")
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    private TipoServico tipoServico;

    @Temporal(TemporalType.DATE)
    @Column(name = "data")
    private Date data;

    @OneToMany
    private List<Pagamento> pagamentos;

    @ManyToOne
    private Professor professor;

    @ManyToOne
    private Aluno aluno;
}