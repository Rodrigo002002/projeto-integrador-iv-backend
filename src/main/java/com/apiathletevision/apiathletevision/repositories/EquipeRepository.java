package com.apiathletevision.apiathletevision.repositories;

import com.apiathletevision.apiathletevision.entities.Equipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipeRepository extends JpaRepository<Equipe, Integer> {
}