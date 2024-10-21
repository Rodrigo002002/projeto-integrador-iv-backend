package com.apiathletevision.apiathletevision.repositories;

import com.apiathletevision.apiathletevision.entities.Plano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanoRepository extends JpaRepository<Plano, Integer>, JpaSpecificationExecutor<Plano> {
    List<Plano> findAllByStatusIsTrue();

    List<Plano> findByStatusIsTrueAndNomeContainingIgnoreCaseOrderByNome(String nome);
}