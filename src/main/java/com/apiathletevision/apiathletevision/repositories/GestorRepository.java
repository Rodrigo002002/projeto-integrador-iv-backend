package com.apiathletevision.apiathletevision.repositories;

import com.apiathletevision.apiathletevision.entities.Gestor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GestorRepository extends JpaRepository<Gestor, UUID> {
}