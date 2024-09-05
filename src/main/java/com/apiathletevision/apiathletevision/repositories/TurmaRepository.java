package com.apiathletevision.apiathletevision.repositories;

import com.apiathletevision.apiathletevision.entities.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Integer> {
}