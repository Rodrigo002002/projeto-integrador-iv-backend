package com.apiathletevision.apiathletevision.services;

import com.apiathletevision.apiathletevision.dtos.ServicoDTO;
import com.apiathletevision.apiathletevision.entities.*;
import com.apiathletevision.apiathletevision.repositories.*;
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

    private final TipoServicoRepository tipoServicoRepository;

    private final ModelMapper modelMapper;

    public List<ServicoDTO> getAllServicos() {
        return servicoRepository.findAll().stream().map(servico -> modelMapper.map(servico, ServicoDTO.class)).toList();
    }

    public Optional<ServicoDTO> getServicoById(Integer id) {
        Optional<Servico> servico = servicoRepository.findById(id);
        ServicoDTO servicoRequestDTO = modelMapper.map(servico, ServicoDTO.class);
        return Optional.ofNullable(servicoRequestDTO);
    }

    public ServicoDTO createServico(ServicoDTO servicoRequestDTO) {
        Servico servico = new Servico();

        Optional<TipoServico> tipoServico = tipoServicoRepository.findById(servicoRequestDTO.getTipoServico());
        servico.setTipoServico(tipoServico.orElse(null));

        List<Pagamento> pagamentos = pagamentoRepository.findAllById(servicoRequestDTO.getPagamentoIds());
        servico.setPagamentos(pagamentos);

        Optional<Professor> professor = professorRepository.findById(servicoRequestDTO.getProfessorId());
        servico.setProfessor(professor.orElse(null));

        Optional<Aluno> aluno = alunoRepository.findById(servicoRequestDTO.getAlunoId());
        servico.setAluno(aluno.orElse(null));

        servico = servicoRepository.save(servico);

        return modelMapper.map(servico, ServicoDTO.class);
    }

    public ServicoDTO updateServico(Integer id, ServicoDTO servicoRequestDTO) {
        Optional<Servico> optionalServico = servicoRepository.findById(id);

        if (optionalServico.isPresent()) {

            Servico servico = optionalServico.get();
            servico.setId(id);

            Optional<TipoServico> tipoServico = tipoServicoRepository.findById(servicoRequestDTO.getTipoServico());
            servico.setTipoServico(tipoServico.orElse(null));

            List<Pagamento> pagamentos = pagamentoRepository.findAllById(servicoRequestDTO.getPagamentoIds());
            servico.setPagamentos(pagamentos);

            Optional<Professor> professor = professorRepository.findById(servicoRequestDTO.getProfessorId());
            servico.setProfessor(professor.orElse(null));

            Optional<Aluno> aluno = alunoRepository.findById(servicoRequestDTO.getAlunoId());
            servico.setAluno(aluno.orElse(null));

            servico = servicoRepository.save(servico);

            return modelMapper.map(servico, ServicoDTO.class);
        }
        return null;
    }

    public void deleteServico(Integer id) {
        servicoRepository.deleteById(id);
    }
}