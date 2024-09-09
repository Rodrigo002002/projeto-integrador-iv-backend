package com.apiathletevision.apiathletevision.repositories;

import com.apiathletevision.apiathletevision.entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {
}