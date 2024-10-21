package com.apiathletevision.apiathletevision.repositories;

import com.apiathletevision.apiathletevision.entities.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer>, JpaSpecificationExecutor<Pagamento> {
    List<Pagamento> findAllByStatusIsTrue();
}