package com.apiathletevision.apiathletevision.repositories;

import com.apiathletevision.apiathletevision.entities.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Integer>, JpaSpecificationExecutor<Turma> {
//    List<Documento> findAllByStatusIsTrue();
}