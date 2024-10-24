package com.apiathletevision.apiathletevision.repositories;

import com.apiathletevision.apiathletevision.entities.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Integer>, JpaSpecificationExecutor<Servico> {
    List<Servico> findAllByStatusIsTrue();

    // Metodo para encontrar serviços pelo ID do aluno
    List<Servico> findAllByAluno_id(UUID alunoId);

    // Metodo para encontrar serviços pelo ID do professor
    List<Servico> findAllByProfessor_id(UUID professorId);
}