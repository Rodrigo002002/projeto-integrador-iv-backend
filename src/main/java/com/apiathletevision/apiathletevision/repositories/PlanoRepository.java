package com.apiathletevision.apiathletevision.repositories;

import com.apiathletevision.apiathletevision.entities.Plano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanoRepository extends JpaRepository<Plano, Integer> {
}