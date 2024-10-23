package com.apiathletevision.apiathletevision.services.impl;

import com.apiathletevision.apiathletevision.dtos.entities.ServicoDTO;
import com.apiathletevision.apiathletevision.dtos.response.PageDTO;
import com.apiathletevision.apiathletevision.dtos.select2.Select2OptionsDTO;
import com.apiathletevision.apiathletevision.entities.*;
import com.apiathletevision.apiathletevision.exeptions.BadRequestException;
import com.apiathletevision.apiathletevision.mappers.PagamentoMapper;
import com.apiathletevision.apiathletevision.mappers.ServicoMapper;
import com.apiathletevision.apiathletevision.repositories.*;
import com.apiathletevision.apiathletevision.services.ServicoService;
import com.apiathletevision.apiathletevision.services.specifications.ServicoSpecification;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ServicoServiceImpl implements ServicoService {

    private final ServicoRepository servicoRepository;
    private final ServicoMapper servicoMapper;
    private final TipoServicoRepository tipoServicoRepository;
    private final PagamentoRepository pagamentoRepository;
    private final PagamentoMapper pagamentoMapper;
    private final ProfessorRepository professorRepository;
    private final AlunoRepository alunoRepository;

    public PageDTO<Servico, ServicoDTO> findAll(int pageNo, int pageSize, String search, Boolean status) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("id"));
        Specification<Servico> specification = ServicoSpecification.getFilters(search, status);
        Page<Servico> servicoPage = servicoRepository.findAll(specification, pageable);
        return new PageDTO<>(servicoPage, servicoMapper::toDto);
    }

    @Override
    public ServicoDTO findById(int id) throws BadRequestException {
        Servico servico = getServico(id);
        return servicoMapper.toDto(servico);
    }

    @Override
    @Transactional
    public ServicoDTO create(ServicoDTO servicoDTO) {
        Servico servico = servicoMapper.toEntity(servicoDTO);

        setCreateAssociations(servicoDTO, servico);

        servico = servicoRepository.save(servico);
        return servicoMapper.toDto(servico);
    }

    @Override
    @Transactional
    public ServicoDTO update(ServicoDTO servicoDTO) throws BadRequestException {
        Servico servico = getServico(servicoDTO.getId());

        servicoMapper.partialUpdate(servicoDTO, servico);

        setCreateAssociations(servicoDTO, servico);

        servico = servicoRepository.save(servico);
        return servicoMapper.toDto(servico);
    }

    @Override
    public void delete(int id) throws BadRequestException {
        Servico servico = getServico(id);
        servicoRepository.delete(servico);
    }

    @Override
    public void changeStatus(int id, boolean status) {
        Optional<Servico> optionalServico = servicoRepository.findById(id);
        if (optionalServico.isPresent()) {
            Servico servico = optionalServico.get();
            servico.setStatus(status);
            servicoRepository.save(servico);
        }
    }

    @Override
    public List<Select2OptionsDTO> findAllToSelect2(String searchTerm) {
        List<Servico> list;

        list = servicoRepository.findAllByStatusIsTrue();

        return list
                .stream()
                .map(data -> new Select2OptionsDTO(data.getId(), data.getData()))
                .collect(Collectors.toList());
    }

    private Servico getServico(int id) throws BadRequestException {
        return servicoRepository.findById(id).orElseThrow(() ->
                new BadRequestException("Servico com o ID: " + id + " não encontrado"));
    }

    private void setCreateAssociations(ServicoDTO servicoDTO, Servico servico) {
        if (servicoDTO.getTipoServicoId() != null) {
            Optional<TipoServico> tipoServico = tipoServicoRepository.findById(servicoDTO.getTipoServicoId());
            servico.setTipoServico(tipoServico.orElse(null));
        }

        if (!servicoDTO.getPagamentoIds().isEmpty()) {
            List<Pagamento> pagamentos = pagamentoRepository.findAllById(servicoDTO.getPagamentoIds());

            servico.setPagamentos(pagamentos);
        }

        if (servicoDTO.getProfessorId() != null) {
            Optional<Professor> professor = professorRepository.findById(servicoDTO.getProfessorId());
            servico.setProfessor(professor.orElse(null));
        }

        if (servicoDTO.getAlunoId() != null) {
            Optional<Aluno> aluno = alunoRepository.findById(servicoDTO.getAlunoId());
            servico.setAluno(aluno.orElse(null));
        }
    }
}