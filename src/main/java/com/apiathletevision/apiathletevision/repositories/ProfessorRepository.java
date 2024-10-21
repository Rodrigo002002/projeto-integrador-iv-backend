package com.apiathletevision.apiathletevision.repositories;

import com.apiathletevision.apiathletevision.entities.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, UUID>, JpaSpecificationExecutor<Professor> {
    List<Professor> findAllByStatusIsTrue();

    List<Professor> findByStatusIsTrueAndNomeContainingIgnoreCaseOrderByNome(String nome);
}