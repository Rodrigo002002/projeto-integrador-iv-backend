package com.apiathletevision.apiathletevision.services;

import com.apiathletevision.apiathletevision.dtos.requests.AlunoRequestDTO;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final ModelMapper modelMapper;

    private final AlunoRepository alunoRepository;

    private final TurmaRepository turmaRepository;

    private final DocumentoRepository documentoRepository;

    private final PlanoRepository planoRepository;

    public List<AlunoResponseDTO> getAllAlunos() {
        return alunoRepository.findAll().stream().map(aluno -> modelMapper.map(aluno, AlunoResponseDTO.class)).toList();
    }

    public Optional<AlunoResponseDTO> getAlunoById(UUID id) {
        Optional<Aluno> aluno = alunoRepository.findById(id);
        AlunoResponseDTO alunoResponseDTO = modelMapper.map(aluno, AlunoResponseDTO.class);
        return Optional.ofNullable(alunoResponseDTO);
    }

    public AlunoResponseDTO createAluno(AlunoRequestDTO alunoRequestDTO) {
        Aluno aluno = new Aluno();

        String encryptedPassword = new BCryptPasswordEncoder().encode(alunoRequestDTO.getPassword());

        aluno.setNome(alunoRequestDTO.getNome());
        aluno.setRole(alunoRequestDTO.getRole());
        aluno.setTelefone(alunoRequestDTO.getTelefone());
        aluno.setEmail(alunoRequestDTO.getEmail());
        aluno.setPassword(encryptedPassword);
        aluno.setRg(alunoRequestDTO.getRg());
        aluno.setCpf(alunoRequestDTO.getCpf());

        if (alunoRequestDTO.getTurmaId() != null) {
            Optional<Turma> turma = turmaRepository.findById(alunoRequestDTO.getTurmaId());
            aluno.setTurma(turma.orElse(null));
        }

        if (alunoRequestDTO.getDocumentosIds() != null && !alunoRequestDTO.getDocumentosIds().isEmpty()) {
            List<Documento> documentos = documentoRepository.findAllById(alunoRequestDTO.getDocumentosIds());
            aluno.setDocumentos(documentos);
        }

        if (alunoRequestDTO.getPlanoId() != null) {
            Optional<Plano> plano = planoRepository.findById(alunoRequestDTO.getPlanoId());
            aluno.setPlano(plano.orElse(null));
        }

        aluno = alunoRepository.save(aluno);

        return modelMapper.map(aluno, AlunoResponseDTO.class);
    }

    public AlunoResponseDTO updateAluno(UUID id, AlunoRequestDTO alunoRequestDTO) {
        Optional<Aluno> optionalAluno = alunoRepository.findById(id);

        if (optionalAluno.isPresent()) {
            String encryptedPassword = new BCryptPasswordEncoder().encode(alunoRequestDTO.getPassword());

            Aluno aluno = optionalAluno.get();

            aluno.setId(id);
            aluno.setNome(alunoRequestDTO.getNome());
            aluno.setRole(alunoRequestDTO.getRole());
            aluno.setTelefone(alunoRequestDTO.getTelefone());
            aluno.setEmail(alunoRequestDTO.getEmail());
            aluno.setPassword(encryptedPassword);
            aluno.setRg(alunoRequestDTO.getRg());
            aluno.setCpf(alunoRequestDTO.getCpf());

            Optional<Turma> turma = turmaRepository.findById(alunoRequestDTO.getTurmaId());
            aluno.setTurma(turma.orElse(null));

            List<Documento> documentos = documentoRepository.findAllById(alunoRequestDTO.getDocumentosIds());
            aluno.setDocumentos(documentos);

            Optional<Plano> plano = planoRepository.findById(alunoRequestDTO.getPlanoId());
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