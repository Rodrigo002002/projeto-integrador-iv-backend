package com.apiathletevision.apiathletevision.repositories;

import com.apiathletevision.apiathletevision.entities.Equipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipeRepository extends JpaRepository<Equipe, Integer>, JpaSpecificationExecutor<Equipe> {
    List<Equipe> findAllByStatusIsTrue();

    List<Equipe> findByStatusIsTrueAndNomeContainingIgnoreCaseOrderByNome(String nome);
}