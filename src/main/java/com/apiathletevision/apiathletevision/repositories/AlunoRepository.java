package com.apiathletevision.apiathletevision.repositories;

import com.apiathletevision.apiathletevision.entities.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, UUID>, JpaSpecificationExecutor<Aluno> {
    List<Aluno> findAllByStatusIsTrue();

    List<Aluno> findByStatusIsTrueAndNomeContainingIgnoreCaseOrderByNome(String nome);
}