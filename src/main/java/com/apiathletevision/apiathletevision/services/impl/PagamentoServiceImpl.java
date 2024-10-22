package com.apiathletevision.apiathletevision.services.impl;

import com.apiathletevision.apiathletevision.dtos.entities.PagamentoDTO;
import com.apiathletevision.apiathletevision.dtos.response.PageDTO;
import com.apiathletevision.apiathletevision.entities.Aluno;
import com.apiathletevision.apiathletevision.entities.Pagamento;
import com.apiathletevision.apiathletevision.entities.Plano;
import com.apiathletevision.apiathletevision.entities.Servico;
import com.apiathletevision.apiathletevision.exeptions.BadRequestException;
import com.apiathletevision.apiathletevision.mappers.PagamentoMapper;
import com.apiathletevision.apiathletevision.repositories.AlunoRepository;
import com.apiathletevision.apiathletevision.repositories.PagamentoRepository;
import com.apiathletevision.apiathletevision.repositories.PlanoRepository;
import com.apiathletevision.apiathletevision.repositories.ServicoRepository;
import com.apiathletevision.apiathletevision.services.PagamentoService;
import com.apiathletevision.apiathletevision.services.specifications.PagamentoSpecification;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PagamentoServiceImpl implements PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final PagamentoMapper pagamentoMapper;
    private final ServicoRepository servicoRepository;
    private final PlanoRepository planoRepository;
    private final AlunoRepository alunoRepository;

    @Override
    public PageDTO<Pagamento, PagamentoDTO> findAll(int pageNo, int pageSize, String search, Boolean status) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("nome"));
        Specification<Pagamento> specification = PagamentoSpecification.getFilters(search, status);
        Page<Pagamento> pagamentoPage = pagamentoRepository.findAll(specification, pageable);
        return new PageDTO<>(pagamentoPage, pagamentoMapper::toDto);
    }

    @Override
    @Transactional
    public PagamentoDTO create(PagamentoDTO pagamentoDTO) {
        Pagamento pagamento = pagamentoMapper.toEntity(pagamentoDTO);

        setCreateAssociations(pagamentoDTO, pagamento);

        pagamento = pagamentoRepository.save(pagamento);
        return pagamentoMapper.toDto(pagamento);
    }

    @Override
    public PagamentoDTO update(PagamentoDTO pagamentoDTO) throws BadRequestException {
        Pagamento pagamento = getPagamento(pagamentoDTO.getId());

        pagamentoMapper.partialUpdate(pagamentoDTO, pagamento);

        setCreateAssociations(pagamentoDTO, pagamento);

        pagamento = pagamentoRepository.save(pagamento);
        return pagamentoMapper.toDto(pagamento);
    }

    @Override
    public PagamentoDTO findById(int id) throws BadRequestException {
        Pagamento pagamento = getPagamento(id);
        return pagamentoMapper.toDto(pagamento);
    }

    @Override
    public void delete(int id) throws BadRequestException {
        Pagamento pagamento = getPagamento(id);
        if (!pagamento.getStatus()) {
            pagamentoRepository.delete(pagamento);
        }
        throw new BadRequestException("Gestor ainda ativo");
    }

    @Override
    public void changeStatus(int id, boolean status) {
        Optional<Pagamento> optionalPagamento = pagamentoRepository.findById(id);
        if (optionalPagamento.isPresent()) {
            Pagamento pagamento = optionalPagamento.get();
            pagamento.setStatus(status);
            pagamentoRepository.save(pagamento);
        }
    }

    private Pagamento getPagamento(int id) throws BadRequestException {
        return pagamentoRepository.findById(id).orElseThrow(() ->
                new BadRequestException("Pagamento com o ID: " + id + " não encontrado"));
    }

    private void setCreateAssociations(PagamentoDTO pagamentoDTO, Pagamento pagamento) {
        if (pagamentoDTO.getPlanoId() != null) {
            Optional<Plano> plano = planoRepository.findById(pagamentoDTO.getPlanoId());
            pagamento.setPlano(plano.orElse(null));
        }

        if (pagamentoDTO.getServicoId() != null) {
            Optional<Servico> servico = servicoRepository.findById(pagamentoDTO.getServicoId());
            pagamento.setServico(servico.orElse(null));
        }

        if (pagamentoDTO.getAlunoId() != null) {
            Optional<Aluno> aluno = alunoRepository.findById(pagamentoDTO.getAlunoId());
            pagamento.setAluno(aluno.orElse(null));
        }
    }
}