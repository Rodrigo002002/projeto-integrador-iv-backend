package com.apiathletevision.apiathletevision.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Table(name = "professores")
@Entity
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Professor extends Usuario {

    @OneToMany
    private List<Documento> documentos;

    @OneToMany
    private List<Servico> servicos;
}
