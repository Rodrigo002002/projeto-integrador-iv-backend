package com.apiathletevision.apiathletevision.repositories;

import com.apiathletevision.apiathletevision.entities.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Integer>, JpaSpecificationExecutor<Documento> {
//    List<Documento> findAllByStatusIsTrue();
}