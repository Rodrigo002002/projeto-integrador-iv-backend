package com.apiathletevision.apiathletevision.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "pagamentos")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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

    @ManyToOne
    private Plano plano;

    @ManyToOne
    private Servico servico;

    @OneToOne
    private Aluno aluno;

    private Boolean pago;

    @ColumnDefault("true")
    private Boolean status;
}
