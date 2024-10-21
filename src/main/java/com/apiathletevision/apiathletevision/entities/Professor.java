package com.apiathletevision.apiathletevision.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Table(name = "professores")
@Entity
@Getter
@Setter
public class Professor extends Usuario {

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "documento_id", referencedColumnName = "id")
    private List<Documento> documentos;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "servico_id", referencedColumnName = "id")
    private List<Servico> servicos;
}
