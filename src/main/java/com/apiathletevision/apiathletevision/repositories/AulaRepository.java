package com.apiathletevision.apiathletevision.repositories;

import com.apiathletevision.apiathletevision.entities.Aula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AulaRepository extends JpaRepository<Aula, Integer>, JpaSpecificationExecutor<Aula> {
    List<Aula> findAllByStatusIsTrue();

    List<Aula> findAllByAlunosPresentes_id(UUID alunoId);

    List<Aula> findAllByProfessor_id(UUID professorId);

    List<Aula> findAllByTurma_id(Integer turmaId);
}