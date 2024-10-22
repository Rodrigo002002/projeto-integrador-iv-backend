package com.apiathletevision.apiathletevision.entities;

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
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "tipoServico_id", referencedColumnName = "id")
    private TipoServico tipoServico;

    private LocalDate data;

    @OneToMany
    @JoinColumn(name = "pagamento_id", referencedColumnName = "id")
    private List<Pagamento> pagamentos;

    @ManyToOne
    @JoinColumn(name = "professor_id", referencedColumnName = "id")
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "aluno_id", referencedColumnName = "id")
    private Aluno aluno;

    @ColumnDefault("true")
    private Boolean status;
}