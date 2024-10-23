package com.apiathletevision.apiathletevision.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "eventos")
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private String descricao;

    private String nome;

    private LocalDate data;

    @ManyToMany
    @JoinTable(
            name = "evento_equipe_equipe",
            joinColumns = @JoinColumn(name = "evento_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "equipe_id", referencedColumnName = "id"))
    private List<Equipe> equipes;

    @ManyToOne
    @JoinColumn(name = "modalidade_id", referencedColumnName = "id")
    private Modalidade modalidade;

    private Boolean status;
}