package com.apiathletevision.apiathletevision.services;

import com.apiathletevision.apiathletevision.dtos.ServicoDTO;
import com.apiathletevision.apiathletevision.entities.Aluno;
import com.apiathletevision.apiathletevision.entities.Pagamento;
import com.apiathletevision.apiathletevision.entities.Professor;
import com.apiathletevision.apiathletevision.entities.Servico;
import com.apiathletevision.apiathletevision.repositories.AlunoRepository;
import com.apiathletevision.apiathletevision.repositories.PagamentoRepository;
import com.apiathletevision.apiathletevision.repositories.ProfessorRepository;
import com.apiathletevision.apiathletevision.repositories.ServicoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServicoService {

    private final ServicoRepository servicoRepository;
    private final PagamentoRepository pagamentoRepository;
    private final ProfessorRepository professorRepository;
    private final AlunoRepository alunoRepository;
    private final ModelMapper modelMapper;

    public List<ServicoDTO> getAllServicos() {
        return servicoRepository.findAll().stream().map(servico -> modelMapper.map(servico, ServicoDTO.class)).toList();
    }

    public Optional<ServicoDTO> getServicoById(Integer id) {
        Optional<Servico> servico = servicoRepository.findById(id);
        ServicoDTO servicoDTO = modelMapper.map(servico, ServicoDTO.class);
        return Optional.ofNullable(servicoDTO);
    }

    public ServicoDTO createServico(ServicoDTO servicoDTO) {
        Servico servico = new Servico();

        servico.setTipo(servicoDTO.getTipo());
        Pagamento pagamento = pagamentoRepository.findById(servicoDTO.getPagamentoId()).orElse(null);
        servico.setPagamento(pagamento);
        Professor professor = professorRepository.findById(servicoDTO.getProfessorId()).orElse(null);
        servico.setProfessor(professor);
        Aluno aluno = alunoRepository.findById(servicoDTO.getAlunoId()).orElse(null);
        servico.setAluno(aluno);

        servico = servicoRepository.save(servico);

        return modelMapper.map(servico, ServicoDTO.class);
    }

    public ServicoDTO updateServico(Integer id, ServicoDTO servicoDTO) {
        Optional<Servico> optionalServico = servicoRepository.findById(id);

        if (optionalServico.isPresent()) {

            Servico servico = optionalServico.get();
            servico.setId(id);
            servico.setTipo(servicoDTO.getTipo());
            Pagamento pagamento = pagamentoRepository.findById(servicoDTO.getPagamentoId()).orElse(null);
            servico.setPagamento(pagamento);
            Professor professor = professorRepository.findById(servicoDTO.getProfessorId()).orElse(null);
            servico.setProfessor(professor);
            Aluno aluno = alunoRepository.findById(servicoDTO.getAlunoId()).orElse(null);
            servico.setAluno(aluno);

            servico = servicoRepository.save(servico);

            return modelMapper.map(servico, ServicoDTO.class);
        }
        return null;
    }

    public void deleteServico(Integer id) {
        servicoRepository.deleteById(id);
    }
}