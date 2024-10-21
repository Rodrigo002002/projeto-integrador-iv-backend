package com.apiathletevision.apiathletevision.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "pagamentos")
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "data_pagamento")
    private LocalDate dataPagamento;

    @Column(name = "data_prazo")
    private LocalDate dataPrazo;

    @Column(name = "valor")
    private Double valor;

    @ManyToOne(cascade = CascadeType.ALL)
    private Plano plano;

    @ManyToOne(cascade = CascadeType.ALL)
    private Servico servico;

    @OneToOne
    @JoinColumn(name = "aluno_id", referencedColumnName = "id")
    private Aluno aluno;

    private Boolean pago;

    @ColumnDefault("true")
    private Boolean status;
}
