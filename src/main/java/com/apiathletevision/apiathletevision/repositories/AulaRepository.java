package com.apiathletevision.apiathletevision.repositories;

import com.apiathletevision.apiathletevision.entities.Aula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AulaRepository extends JpaRepository<Aula, Integer> {
}