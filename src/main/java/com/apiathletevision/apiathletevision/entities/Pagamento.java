package com.apiathletevision.apiathletevision.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Getter
@Setter
@Table(name = "pagamentos")
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Temporal(TemporalType.DATE)
    @Column(name = "dataPagamento")
    private Date dataPagamento;

    @Temporal(TemporalType.DATE)
    @Column(name = "dataPrazo")
    private Date dataPrazo;

    @Column(name = "valor")
    private Double valor;

    @ManyToOne(cascade = CascadeType.ALL)
    private Plano plano;

    @ManyToOne(cascade = CascadeType.ALL)
    private Servico servico;

    @Column(name = "pago")
    private Boolean pago;
}
