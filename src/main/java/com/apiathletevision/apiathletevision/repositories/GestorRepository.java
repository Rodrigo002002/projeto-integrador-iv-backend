package com.apiathletevision.apiathletevision.repositories;

import com.apiathletevision.apiathletevision.entities.Gestor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GestorRepository extends JpaRepository<Gestor, UUID>, JpaSpecificationExecutor<Gestor> {
    List<Gestor> findAllByStatusIsTrue();

    List<Gestor> findByStatusIsTrueAndNomeContainingIgnoreCaseOrderByNome(String nome);
}