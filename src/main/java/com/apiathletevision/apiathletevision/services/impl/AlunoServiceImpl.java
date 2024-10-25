package com.apiathletevision.apiathletevision.services.impl;

import com.apiathletevision.apiathletevision.dtos.entities.*;
import com.apiathletevision.apiathletevision.dtos.response.PageDTO;
import com.apiathletevision.apiathletevision.dtos.select2.Select2OptionsDTO;
import com.apiathletevision.apiathletevision.entities.*;
import com.apiathletevision.apiathletevision.exeptions.BadRequestException;
import com.apiathletevision.apiathletevision.mappers.*;
import com.apiathletevision.apiathletevision.repositories.*;
import com.apiathletevision.apiathletevision.services.AlunoService;
import com.apiathletevision.apiathletevision.services.specifications.AlunoSpecification;
import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class AlunoServiceImpl implements AlunoService {

    private final AlunoRepository alunoRepository;
    private final DocumentoRepository documentoRepository;
    private final PlanoRepository planoRepository;
    private final AlunoMapper alunoMapper;
    private final DocumentoMapper documentoMapper;
    private final TurmaRepository turmaRepository;
    private final TurmaMapper turmaMapper;
    private final AulaRepository aulaRepository;
    private final AulaMapper aulaMapper;
    private final PagamentoRepository pagamentoRepository;
    private final PagamentoMapper pagamentoMapper;
    private final ServicoRepository servicoRepository;
    private final ServicoMapper servicoMapper;

    @Override
    public PageDTO<Aluno, AlunoDTO> findAll(int pageNo, int pageSize, String search, Boolean status) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("nome"));
        Specification<Aluno> specification = AlunoSpecification.getFilters(search, status);
        Page<Aluno> alunoPage = alunoRepository.findAll(specification, pageable);
        return new PageDTO<>(alunoPage, alunoMapper::toDto);
    }

    @Override
    @Transactional
    public AlunoDTO create(AlunoDTO alunoDTO) {
        Aluno aluno = alunoMapper.toEntity(alunoDTO);

        String encryptedPassword = new BCryptPasswordEncoder().encode(alunoDTO.getPassword());
        aluno.setPassword(encryptedPassword);
        aluno.setLogin(alunoDTO.getLogin());
        aluno.setStatus(alunoDTO.getStatus());

        setCreateAssociations(alunoDTO, aluno);

        aluno = alunoRepository.save(aluno);
        return alunoMapper.toDto(aluno);
    }

    @Override
    public AlunoDTO update(AlunoDTO alunoDTO) throws BadRequestException {
        Aluno aluno = getAluno(alunoDTO.getId());
        String encryptedPassword = new BCryptPasswordEncoder().encode(alunoDTO.getPassword());
        aluno.setPassword(encryptedPassword);

        alunoMapper.partialUpdate(alunoDTO, aluno);

        setCreateAssociations(alunoDTO, aluno);

        aluno = alunoRepository.save(aluno);
        return alunoMapper.toDto(aluno);
    }

    @Override
    public AlunoDTO findById(UUID id) throws BadRequestException {
        Aluno aluno = getAluno(id);
        return alunoMapper.toDto(aluno);
    }

    @Override
    public void delete(UUID id) throws BadRequestException {
        Aluno aluno = getAluno(id);
        if (!aluno.getStatus()) {
            alunoRepository.delete(aluno);
        }
        throw new BadRequestException("Aluno ainda ativo");
    }

    @Override
    public void changeStatus(UUID id, boolean status) {
        Optional<Aluno> optionalAluno = alunoRepository.findById(id);
        if (optionalAluno.isPresent()) {
            Aluno aluno = optionalAluno.get();
            aluno.setStatus(status);
            alunoRepository.save(aluno);
        }
    }

    private Aluno getAluno(UUID id) throws BadRequestException {
        return alunoRepository.findById(id).orElseThrow(() ->
                new BadRequestException("Aluno com o ID: " + id + " não encontrado"));
    }

    @Override
    public List<Select2OptionsDTO> findAllToSelect2(String searchTerm) {
        List<Aluno> list;

        if (StringUtils.isBlank(searchTerm)) {
            list = alunoRepository.findAllByStatusIsTrue();
        } else {
            list = alunoRepository.findByStatusIsTrueAndNomeContainingIgnoreCaseOrderByNome(searchTerm);
        }

        return list
                .stream()
                .map(data -> new Select2OptionsDTO(data.getId(), data.getNome()))
                .collect(Collectors.toList());
    }

    @Override
    public List<PagamentoDTO> findAllPagamentoByAlunoId(UUID id) throws BadRequestException {
        List<Pagamento> pagamentos = pagamentoRepository.findAllByAluno_id(id);
        if (pagamentos.isEmpty()) {
            throw new BadRequestException("Nenhum pagamento encontrado");
        }
        return pagamentoMapper.toDtoList(pagamentos);
    }

    @Override
    public List<AulaDTO> findAllAulaByAlunoId(UUID id) throws BadRequestException {
        List<Aula> aulas = aulaRepository.findAllByAlunosPresentes_id(id);
        if (aulas.isEmpty()) {
            throw new BadRequestException("Nenhuma aula encontrada");
        }
        return aulaMapper.toDtoList(aulas);
    }

    @Override
    public List<TurmaDTO> findAllTurmaByAlunoId(UUID id) throws BadRequestException {
        List<Turma> turmas = turmaRepository.findAllByAlunos_id(id);
        if (turmas.isEmpty()) {
            throw new BadRequestException("Nenhuma turma encontrada");
        }
        return turmaMapper.toDtoList(turmas);
    }

    @Override
    public List<ServicoDTO> findAllServicoByAlunoId(UUID id) throws BadRequestException {
        List<Servico> servicos = servicoRepository.findAllByAluno_id(id);
        if (servicos.isEmpty()) {
            throw new BadRequestException("Nenhum serviço encontrado");
        }
        return servicoMapper.toDtoList(servicos);
    }

    @Override
    public List<PagamentoDTO> findAllPagamentoByAlunoIdAndPlanoId(UUID id, int pagamentoId) {
        List<Pagamento> pagamentos = pagamentoRepository.findAllByAluno_idAndPlano_id(id, pagamentoId);
        if (pagamentos.isEmpty()) {
            throw new BadRequestException("Nenhum pagamento encontrado");
        }
        return pagamentoMapper.toDtoList(pagamentos);
    }

    private void setCreateAssociations(AlunoDTO alunoDTO, Aluno aluno) {

        if (!alunoDTO.getDocumentos().isEmpty()) {
            List<Documento> documentos = alunoDTO.getDocumentos()
                    .stream()
                    .map(documentoDTO -> {
                                if (documentoDTO.getId() != null) {
                                    Optional<Documento> documento = documentoRepository.findById(documentoDTO.getId());
                                    return documento.orElse(null);
                                }
                                return documentoRepository.save(
                                        documentoMapper.toEntity(documentoDTO)
                                );
                            }
                    ).toList();

            aluno.setDocumentos(documentos);
        }

        if (alunoDTO.getPlanoId() != null) {
            Optional<Plano> plano = planoRepository.findById(alunoDTO.getPlanoId());
            aluno.setPlano(plano.orElse(null));
        }
    }
}