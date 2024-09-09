package com.apiathletevision.apiathletevision.services;

import com.apiathletevision.apiathletevision.dtos.ServicoDTO;
import com.apiathletevision.apiathletevision.entities.Servico;
import com.apiathletevision.apiathletevision.entities.Aluno;
import com.apiathletevision.apiathletevision.entities.Pagamento;
import com.apiathletevision.apiathletevision.entities.Professor;
import com.apiathletevision.apiathletevision.repositories.ServicoRepository;
import com.apiathletevision.apiathletevision.repositories.AlunoRepository;
import com.apiathletevision.apiathletevision.repositories.PagamentoRepository;
import com.apiathletevision.apiathletevision.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    public List<Servico> getAllServicos() {
        return servicoRepository.findAll();
    }

    public Optional<Servico> getServicoById(Integer id) {
        return servicoRepository.findById(id);
    }

    public Servico createServico(ServicoDTO servicoDTO) {
        Servico servico = new Servico();
        servico.setTipo(servicoDTO.getTipo());

        Pagamento pagamento = pagamentoRepository.findById(servicoDTO.getPagamentoId()).orElse(null);
        servico.setPagamento(pagamento);

        Professor professor = professorRepository.findById(servicoDTO.getProfessorId()).orElse(null);
        servico.setProfessor(professor);

        Aluno aluno = alunoRepository.findById(servicoDTO.getAlunoId()).orElse(null);
        servico.setAluno(aluno);

        return servicoRepository.save(servico);
    }

    public Servico updateServico(Integer id, ServicoDTO servicoDTO) {
        Optional<Servico> optionalServico = servicoRepository.findById(id);
        if (optionalServico.isPresent()) {
            Servico servico = optionalServico.get();
            servico.setTipo(servicoDTO.getTipo());

            Pagamento pagamento = pagamentoRepository.findById(servicoDTO.getPagamentoId()).orElse(null);
            servico.setPagamento(pagamento);

            Professor professor = professorRepository.findById(servicoDTO.getProfessorId()).orElse(null);
            servico.setProfessor(professor);

            Aluno aluno = alunoRepository.findById(servicoDTO.getAlunoId()).orElse(null);
            servico.setAluno(aluno);

            return servicoRepository.save(servico);
        }
        return null;
    }

    public void deleteServico(Integer id) {
        servicoRepository.deleteById(id);
    }
}