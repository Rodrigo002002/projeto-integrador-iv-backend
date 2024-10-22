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

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "preco")
    private Double preco;

    @ColumnDefault("true")
    private Boolean status;
}
