package com.apiathletevision.apiathletevision.repositories;

import com.apiathletevision.apiathletevision.entities.Modalidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModalidadeRepository extends JpaRepository<Modalidade, Integer> {
}