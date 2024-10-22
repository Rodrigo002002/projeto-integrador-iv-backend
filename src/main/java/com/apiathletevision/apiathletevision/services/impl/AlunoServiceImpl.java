package com.apiathletevision.apiathletevision.services.impl;

import com.apiathletevision.apiathletevision.dtos.entities.AlunoDTO;
import com.apiathletevision.apiathletevision.dtos.response.PageDTO;
import com.apiathletevision.apiathletevision.dtos.select2.Select2OptionsDTO;
import com.apiathletevision.apiathletevision.entities.Aluno;
import com.apiathletevision.apiathletevision.entities.Documento;
import com.apiathletevision.apiathletevision.entities.Plano;
import com.apiathletevision.apiathletevision.entities.Turma;
import com.apiathletevision.apiathletevision.exeptions.BadRequestException;
import com.apiathletevision.apiathletevision.mappers.AlunoMapper;
import com.apiathletevision.apiathletevision.mappers.DocumentoMapper;
import com.apiathletevision.apiathletevision.repositories.AlunoRepository;
import com.apiathletevision.apiathletevision.repositories.DocumentoRepository;
import com.apiathletevision.apiathletevision.repositories.PlanoRepository;
import com.apiathletevision.apiathletevision.repositories.TurmaRepository;
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
    private final TurmaRepository turmaRepository;
    private final DocumentoRepository documentoRepository;
    private final PlanoRepository planoRepository;
    private final AlunoMapper alunoMapper;
    private final DocumentoMapper documentoMapper;

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

    private void setCreateAssociations(AlunoDTO alunoDTO, Aluno aluno) {
        if (alunoDTO.getTurmaId() != null) {
            Optional<Turma> turma = turmaRepository.findById(alunoDTO.getTurmaId());
            aluno.setTurma(turma.orElse(null));
        }

        if (alunoDTO.getPlano() != null) {
            Optional<Plano> plano = planoRepository.findById(alunoDTO.getPlanoId());
            aluno.setPlano(plano.orElse(null));
        }

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
    }
}