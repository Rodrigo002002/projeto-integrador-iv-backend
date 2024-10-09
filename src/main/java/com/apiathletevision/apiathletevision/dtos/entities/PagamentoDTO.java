package com.apiathletevision.apiathletevision.dtos.entities;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class PagamentoDTO {
    private Integer id;
    private Date dataPagamento;
    private Date dataPrazo;
    private Double valor;
    private Boolean pago;
}