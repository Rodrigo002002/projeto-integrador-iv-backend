package com.apiathletevision.apiathletevision.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "professores")
public class Professor extends Usuario {

    @OneToMany(cascade = CascadeType.ALL)
    private List<Documento> documentos;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Servico> servicos;
}
