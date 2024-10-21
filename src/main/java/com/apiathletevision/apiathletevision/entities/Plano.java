package com.apiathletevision.apiathletevision.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "planos")
public class Plano {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private String nome;

    private String tipo;

    private List<String> beneficios;

    private double preco;

    @ManyToMany
    private List<Modalidade> modalidades;

    @OneToMany
    private List<Pagamento> pagamentos;

    private Boolean status;
}