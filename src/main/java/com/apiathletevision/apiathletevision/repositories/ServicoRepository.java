package com.apiathletevision.apiathletevision.repositories;

import com.apiathletevision.apiathletevision.entities.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Integer>, JpaSpecificationExecutor<Servico> {
    List<Servico> findAllByStatusIsTrue();
}