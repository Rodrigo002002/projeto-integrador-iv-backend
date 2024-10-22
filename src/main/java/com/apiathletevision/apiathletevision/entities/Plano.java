package com.apiathletevision.apiathletevision.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

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
    @JoinTable(
            name = "plano_modalidades_modalidades",
            joinColumns = @JoinColumn(name = "plano_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "modalidade_id", referencedColumnName = "id"))
    private List<Modalidade> modalidades;

    @OneToMany
    @JoinColumn(name = "pagamento_id", referencedColumnName = "id")
    private List<Pagamento> pagamentos;

    @ColumnDefault("true")
    private Boolean status;
}