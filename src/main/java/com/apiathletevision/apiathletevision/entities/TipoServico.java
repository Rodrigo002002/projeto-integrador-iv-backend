package com.apiathletevision.apiathletevision.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@Setter
@Table(name = "tiposServicos")
public class TipoServico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "preco", nullable = false)
    private Double preco;

    @Column(name = "status", nullable = false)
    @ColumnDefault("true")
    private Boolean status;
}
