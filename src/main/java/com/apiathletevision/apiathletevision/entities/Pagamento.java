package com.apiathletevision.apiathletevision.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "pagamentos")
@AllArgsConstructor
@NoArgsConstructor
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "data_pagamento")
    private LocalDate dataPagamento;

    @Column(name = "data_prazo")
    private LocalDate dataPrazo;

    private Double valor;

    @ManyToOne
    @JoinColumn(name = "plano_id")
    private Plano plano;

    @ManyToOne
    @JoinColumn(name = "servico_id")
    private Servico servico;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    private Boolean pago;

    @ColumnDefault("true")
    private Boolean status;
}
