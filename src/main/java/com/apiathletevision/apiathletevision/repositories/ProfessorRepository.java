package com.apiathletevision.apiathletevision.repositories;

import com.apiathletevision.apiathletevision.entities.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, UUID> {
}