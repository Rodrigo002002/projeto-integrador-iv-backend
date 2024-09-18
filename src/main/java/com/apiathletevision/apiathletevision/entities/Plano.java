package com.apiathletevision.apiathletevision.entities;

import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "planos")
public class Plano {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "beneficio")
    private List<String> beneficios;

    @Column(name = "preco")
    private Double preco;

    @ManyToMany
    @Column(name = "modalidades")
    private List<Modalidade> modalidades;

    @OneToMany
    @Column(name = "pagamentos")
    private List<Pagamento> pagamentos;
}