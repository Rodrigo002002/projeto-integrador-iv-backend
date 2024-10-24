package com.apiathletevision.apiathletevision.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "planos")
@AllArgsConstructor
@NoArgsConstructor
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

    @ColumnDefault("true")
    private Boolean status;
}