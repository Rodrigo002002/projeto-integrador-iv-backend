package com.apiathletevision.apiathletevision.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "aulas")
@AllArgsConstructor
@NoArgsConstructor
public class Aula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate data;

    @ManyToMany
    @JoinTable(
            name = "aula_aluno_presente",
            joinColumns = @JoinColumn(name = "aula_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "aluno_id", referencedColumnName = "id"))
    private List<Aluno> alunosPresentes;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "turma_id")
    private Turma turma;

    @ColumnDefault("true")
    private Boolean status;
}
