package com.apiathletevision.apiathletevision.repositories;

import com.apiathletevision.apiathletevision.entities.TipoServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoServicoRepository extends JpaRepository<TipoServico, Integer> {
}
