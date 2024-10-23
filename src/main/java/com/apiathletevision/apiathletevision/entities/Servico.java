package com.apiathletevision.apiathletevision.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "servicos")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    private TipoServico tipoServico;

    @Column(name = "data", nullable = false)
    private LocalDate data;

    @OneToMany
    @JoinColumn(name = "pagamento_id")
    private List<Pagamento> pagamentos;

    @ManyToOne
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @Column(name = "status", nullable = false)
    @ColumnDefault("true")
    private Boolean status;
}