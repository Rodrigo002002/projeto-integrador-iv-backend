package com.apiathletevision.apiathletevision.repositories;

import com.apiathletevision.apiathletevision.entities.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer>, JpaSpecificationExecutor<Pagamento> {
    List<Pagamento> findAllByStatusIsTrue();

    // Metodo para encontrar pagamento pelo id do aluno
    List<Pagamento> findAllByAluno_id(UUID alunoId);

    // Metodo para encontrar pagamento pelo id do servico
    List<Pagamento> findAllByServico_id(Integer servicoId);

    // Metodo para encontrar pagamento pelo ID do aluno e pelo ID do plano
    List<Pagamento> findAllByAluno_idAndPlano_id(UUID alunoId, Integer planoId);
}