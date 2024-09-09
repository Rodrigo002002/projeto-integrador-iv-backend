package com.apiathletevision.apiathletevision.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PlanoDTO {
    private Integer id;
    private String nome;
    private String tipo;
    private Double preco;
    private List<String> beneficios;
    private List<Integer> modalidadesIds;
    private List<Integer> pagamentosIds;
}