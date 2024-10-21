package com.apiathletevision.apiathletevision.repositories;

import com.apiathletevision.apiathletevision.entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer>, JpaSpecificationExecutor<Evento> {
    List<Evento> findAllByStatusIsTrue();

    List<Evento> findByStatusIsTrueAndNomeContainingIgnoreCaseOrderByNome(String nome);
}