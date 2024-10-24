package com.apiathletevision.apiathletevision.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "professores")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Professor extends Usuario {

    @OneToMany
    private List<Documento> documentos;
}
