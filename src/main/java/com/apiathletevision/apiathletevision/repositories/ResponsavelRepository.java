package com.apiathletevision.apiathletevision.repositories;

import com.apiathletevision.apiathletevision.entities.Responsavel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ResponsavelRepository extends JpaRepository<Responsavel, UUID> {
}