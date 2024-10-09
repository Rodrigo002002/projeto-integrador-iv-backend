package com.apiathletevision.apiathletevision.services.impl;

import com.apiathletevision.apiathletevision.dtos.entities.AlunoDTO;
import com.apiathletevision.apiathletevision.dtos.response.PageDTO;
import com.apiathletevision.apiathletevision.dtos.responses.AlunoResponseDTO;
import com.apiathletevision.apiathletevision.entities.Aluno;
import com.apiathletevision.apiathletevision.entities.Documento;
import com.apiathletevision.apiathletevision.entities.Plano;
import com.apiathletevision.apiathletevision.entities.Turma;
import com.apiathletevision.apiathletevision.repositories.AlunoRepository;
import com.apiathletevision.apiathletevision.repositories.DocumentoRepository;
import com.apiathletevision.apiathletevision.repositories.PlanoRepository;
import com.apiathletevision.apiathletevision.repositories.TurmaRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AlunoServiceImpl {

    private final ModelMapper modelMapper;
    private final AlunoRepository alunoRepository;
    private final TurmaRepository turmaRepository;
    private final DocumentoRepository documentoRepository;
    private final PlanoRepository planoRepository;

    public PageDTO<AlunoDTO> findAll(int pageNo, int pageSize, String search, Boolean status, String tipoPessoa) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("nome"));

        Specification<Aluno> specification = PessoaSpecification.getFilters(search, status);
    }

    public Optional<AlunoResponseDTO> getAlunoById(UUID id) {
        Optional<Aluno> aluno = alunoRepository.findById(id);
        AlunoResponseDTO alunoResponseDTO = modelMapper.map(aluno, AlunoResponseDTO.class);
        return Optional.ofNullable(alunoResponseDTO);
    }

    public AlunoResponseDTO createAluno(AlunoDTO alunoDTO) {
        Aluno aluno = new Aluno();

        String encryptedPassword = new BCryptPasswordEncoder().encode(alunoDTO.getPassword());

        aluno.setNome(alunoDTO.getNome());
        aluno.setRole(alunoDTO.getRole());
        aluno.setTelefone(alunoDTO.getTelefone());
        aluno.setEmail(alunoDTO.getEmail());
        aluno.setPassword(encryptedPassword);
        aluno.setRg(alunoDTO.getRg());
        aluno.setCpf(alunoDTO.getCpf());

        if (alunoDTO.getTurmaId() != null) {
            Optional<Turma> turma = turmaRepository.findById(alunoDTO.getTurmaId());
            aluno.setTurma(turma.orElse(null));
        }

        if (alunoDTO.getDocumentosIds() != null && !alunoDTO.getDocumentosIds().isEmpty()) {
            List<Documento> documentos = documentoRepository.findAllById(alunoDTO.getDocumentosIds());
            aluno.setDocumentos(documentos);
        }

        if (alunoDTO.getPlanoId() != null) {
            Optional<Plano> plano = planoRepository.findById(alunoDTO.getPlanoId());
            aluno.setPlano(plano.orElse(null));
        }

        aluno = alunoRepository.save(aluno);

        return modelMapper.map(aluno, AlunoResponseDTO.class);
    }

    public AlunoResponseDTO updateAluno(UUID id, AlunoDTO alunoDTO) {
        Optional<Aluno> optionalAluno = alunoRepository.findById(id);

        if (optionalAluno.isPresent()) {
            String encryptedPassword = new BCryptPasswordEncoder().encode(alunoDTO.getPassword());

            Aluno aluno = optionalAluno.get();

            aluno.setId(id);
            aluno.setNome(alunoDTO.getNome());
            aluno.setRole(alunoDTO.getRole());
            aluno.setTelefone(alunoDTO.getTelefone());
            aluno.setEmail(alunoDTO.getEmail());
            aluno.setPassword(encryptedPassword);
            aluno.setRg(alunoDTO.getRg());
            aluno.setCpf(alunoDTO.getCpf());

            Optional<Turma> turma = turmaRepository.findById(alunoDTO.getTurmaId());
            aluno.setTurma(turma.orElse(null));

            List<Documento> documentos = documentoRepository.findAllById(alunoDTO.getDocumentosIds());
            aluno.setDocumentos(documentos);

            Optional<Plano> plano = planoRepository.findById(alunoDTO.getPlanoId());
            aluno.setPlano(plano.orElse(null));

            aluno = alunoRepository.save(aluno);

            return modelMapper.map(aluno, AlunoResponseDTO.class);
        }
        return null;
    }

    public void deleteAluno(UUID id) {
        alunoRepository.deleteById(id);
    }
}