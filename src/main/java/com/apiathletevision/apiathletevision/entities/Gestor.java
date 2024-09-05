package com.apiathletevision.apiathletevision.entities;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "gestores")
public class Gestor extends Usuario {
    @ElementCollection
    private List<String> funcionalidades;
}