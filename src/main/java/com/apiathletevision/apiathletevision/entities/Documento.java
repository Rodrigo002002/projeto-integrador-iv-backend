package com.apiathletevision.apiathletevision.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Entity
@Getter
@Setter
@Table(name = "documentos")
public class Documento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "imagem", length = 2000000)
    private String imagem;
}
