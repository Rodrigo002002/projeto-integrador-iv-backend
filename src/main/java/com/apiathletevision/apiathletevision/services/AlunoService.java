package com.apiathletevision.apiathletevision.services;

import com.apiathletevision.apiathletevision.dtos.AlunoDTO;
import com.apiathletevision.apiathletevision.entities.Aluno;
import com.apiathletevision.apiathletevision.enums.UserRole;
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

    public List<AlunoDTO> getAllAlunos() {
        return alunoRepository.findAll().stream().map(aluno -> modelMapper.map(aluno, AlunoDTO.class)).toList();
    }

    public Optional<AlunoDTO> getAlunoById(UUID id) {
        Optional<Aluno> aluno = alunoRepository.findById(id);
        AlunoDTO alunoDTO = modelMapper.map(aluno, AlunoDTO.class);
        return Optional.ofNullable(alunoDTO);
    }

    public AlunoDTO createAluno(AlunoDTO alunoDTO) {
        Aluno aluno = new Aluno();

        String encryptedPassword = new BCryptPasswordEncoder().encode(alunoDTO.getPassword());
        aluno.setNome(alunoDTO.getNome());
        aluno.setRole(UserRole.ALUNO);
        aluno.setTelefone(alunoDTO.getTelefone());
        aluno.setEmail(alunoDTO.getEmail());
        aluno.setPassword(encryptedPassword);
        aluno.setTurma(alunoDTO.getTurmaId() == null ? null : turmaRepository.findById(alunoDTO.getTurmaId()).orElse(null));
        aluno.setDocumentos(documentoRepository.findAllById(alunoDTO.getDocumentosIds()));
        aluno.setPlano(alunoDTO.getPlanoId() == null ? null : planoRepository.findById(alunoDTO.getPlanoId()).orElse(null));
        aluno.setRg(alunoDTO.getRg());
        aluno.setCpf(alunoDTO.getCpf());
        aluno = alunoRepository.save(aluno);

        return modelMapper.map(aluno, AlunoDTO.class);
    }

    public AlunoDTO updateAluno(UUID id, AlunoDTO alunoDTO) {
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
            aluno.setTurma(turmaRepository.findById(alunoDTO.getTurmaId()).orElse(null));
            aluno.setDocumentos(documentoRepository.findAllById(alunoDTO.getDocumentosIds()));
            aluno.setPlano(planoRepository.findById(alunoDTO.getPlanoId()).orElse(null));
            aluno.setRg(alunoDTO.getRg());
            aluno.setCpf(alunoDTO.getCpf());

            aluno = alunoRepository.save(aluno);

            return modelMapper.map(aluno, AlunoDTO.class);
        }
        return null;
    }

    public void deleteAluno(UUID id) {
        alunoRepository.deleteById(id);
    }
}