package com.apiathletevision.apiathletevision.repositories;

import com.apiathletevision.apiathletevision.entities.TipoServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoServicoRepository extends JpaRepository<TipoServico, Integer>, JpaSpecificationExecutor<TipoServico> {
    List<TipoServico> findAllByStatusIsTrue();

    List<TipoServico> findByStatusIsTrueAndTipoContainingIgnoreCaseOrderByTipo(String tipo);
}
