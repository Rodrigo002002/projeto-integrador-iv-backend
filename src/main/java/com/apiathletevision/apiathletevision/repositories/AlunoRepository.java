package com.apiathletevision.apiathletevision.repositories;

import com.apiathletevision.apiathletevision.entities.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}