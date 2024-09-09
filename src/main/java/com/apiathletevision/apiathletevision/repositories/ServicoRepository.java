package com.apiathletevision.apiathletevision.repositories;

import com.apiathletevision.apiathletevision.entities.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Integer> {
}