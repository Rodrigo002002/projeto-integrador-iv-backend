package com.apiathletevision.apiathletevision.repositories;

import com.apiathletevision.apiathletevision.entities.Modalidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModalidadeRepository extends JpaRepository<Modalidade, Integer>, JpaSpecificationExecutor<Modalidade> {
    List<Modalidade> findAllByStatusIsTrue();

    List<Modalidade> findByStatusIsTrueAndNomeContainingIgnoreCaseOrderByNome(String nome);
}