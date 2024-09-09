package com.apiathletevision.apiathletevision.repositories;

import com.apiathletevision.apiathletevision.entities.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
}