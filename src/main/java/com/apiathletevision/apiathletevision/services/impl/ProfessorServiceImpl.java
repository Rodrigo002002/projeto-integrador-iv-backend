package com.apiathletevision.apiathletevision.services.impl;

import com.apiathletevision.apiathletevision.dtos.entities.ProfessorDTO;
import com.apiathletevision.apiathletevision.dtos.response.PageDTO;
import com.apiathletevision.apiathletevision.dtos.select2.Select2OptionsDTO;
import com.apiathletevision.apiathletevision.entities.Documento;
import com.apiathletevision.apiathletevision.entities.Professor;
import com.apiathletevision.apiathletevision.entities.Servico;
import com.apiathletevision.apiathletevision.exeptions.BadRequestException;
import com.apiathletevision.apiathletevision.mappers.DocumentoMapper;
import com.apiathletevision.apiathletevision.mappers.ProfessorMapper;
import com.apiathletevision.apiathletevision.mappers.ServicoMapper;
import com.apiathletevision.apiathletevision.repositories.DocumentoRepository;
import com.apiathletevision.apiathletevision.repositories.ProfessorRepository;
import com.apiathletevision.apiathletevision.repositories.ServicoRepository;
import com.apiathletevision.apiathletevision.services.ProfessorService;
import com.apiathletevision.apiathletevision.services.specifications.ProfessorSpecification;
import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
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
public class ProfessorServiceImpl implements ProfessorService {

    private final ProfessorRepository professorRepository;
    private final ProfessorMapper professorMapper;
    private final DocumentoRepository documentoRepository;
    private final DocumentoMapper documentoMapper;
    private final ServicoRepository servicoRepository;
    private final ServicoMapper servicoMapper;

    @Override
    public PageDTO<Professor, ProfessorDTO> findAll(int pageNo, int pageSize, String search, Boolean status) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("nome"));
        Specification<Professor> specification = ProfessorSpecification.getFilters(search, status);
        Page<Professor> alunoPage = professorRepository.findAll(specification, pageable);
        return new PageDTO<>(alunoPage, professorMapper::toDto);
    }

    @Override
    @Transactional
    public ProfessorDTO create(ProfessorDTO professorDTO) {
        Professor professor = professorMapper.toEntity(professorDTO);

        String encryptedPassword = new BCryptPasswordEncoder().encode(professorDTO.getPassword());
        professor.setPassword(encryptedPassword);
        professor.setLogin(professorDTO.getLogin());
        professor.setStatus(professorDTO.getStatus());

        setCreateAssociations(professorDTO, professor);

        professor = professorRepository.save(professor);
        return professorMapper.toDto(professor);
    }

    @Override
    public ProfessorDTO update(ProfessorDTO professorDTO) throws BadRequestException {
        Professor professor = getProfessor(professorDTO.getId());
        String encryptedPassword = new BCryptPasswordEncoder().encode(professorDTO.getPassword());

        professorMapper.partialUpdate(professorDTO, professor);

        professor.setPassword(encryptedPassword);
        setCreateAssociations(professorDTO, professor);

        professor = professorRepository.save(professor);

        return professorMapper.toDto(professor);
    }

    @Override
    public ProfessorDTO findById(UUID id) throws BadRequestException {
        Professor professor = getProfessor(id);
        return professorMapper.toDto(professor);
    }

    @Override
    public void delete(UUID id) throws BadRequestException {
        Professor professor = getProfessor(id);
        if (!professor.getStatus()) {
            professorRepository.delete(professor);
        }
        throw new BadRequestException("Pessoa ainda ativa");
    }

    @Override
    public void changeStatus(UUID id, boolean status) {
        Optional<Professor> optionalAluno = professorRepository.findById(id);
        if (optionalAluno.isPresent()) {
            Professor professor = optionalAluno.get();
            professor.setStatus(status);
            professorRepository.save(professor);
        }
    }

    private Professor getProfessor(UUID id) throws BadRequestException {
        return professorRepository.findById(id).orElseThrow(() ->
                new BadRequestException("Aluno com o ID: " + id + " não encontrado"));
    }

    @Override
    public List<Select2OptionsDTO> findAllToSelect2(String searchTerm) {
        List<Professor> list;

        if (StringUtils.isBlank(searchTerm)) {
            list = professorRepository.findAllByStatusIsTrue();
        } else {
            list = professorRepository.findByStatusIsTrueAndNomeContainingIgnoreCaseOrderByNome(searchTerm);
        }

        return list
                .stream()
                .map(data -> new Select2OptionsDTO(data.getId(), data.getNome()))
                .collect(Collectors.toList());
    }

    private void setCreateAssociations(ProfessorDTO professorDTO, Professor professor) {
        if (!professorDTO.getServicosIds().isEmpty()) {
            List<Servico> servicos = servicoRepository.findAllById(professorDTO.getServicosIds());
            professor.setServicos(servicos);
        }

        if (!professorDTO.getDocumentos().isEmpty()) {
            List<Documento> documentos = professorDTO.getDocumentos()
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

            professor.setDocumentos(documentos);
        }
    }
}