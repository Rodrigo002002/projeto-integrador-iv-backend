package com.apiathletevision.apiathletevision.repositories;

import com.apiathletevision.apiathletevision.entities.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Integer>, JpaSpecificationExecutor<Turma> {
    List<Turma> findAllByAlunos_id(UUID alunoId);

    List<Turma>  findAllByProfessor_id(UUID professorId);

    List<Turma>  findAllByModalidade_id(Integer modalidadeId);
}