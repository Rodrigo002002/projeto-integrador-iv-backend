package com.apiathletevision.apiathletevision.services;

import com.apiathletevision.apiathletevision.dtos.AlunoDTO;
import com.apiathletevision.apiathletevision.entities.Aluno;
import com.apiathletevision.apiathletevision.repositories.AlunoRepository;
import com.apiathletevision.apiathletevision.repositories.TurmaRepository;
import com.apiathletevision.apiathletevision.repositories.DocumentoRepository;
import com.apiathletevision.apiathletevision.repositories.PlanoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AlunoService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private DocumentoRepository documentoRepository;

    @Autowired
    private PlanoRepository planoRepository;

    public List<Aluno> getAllAlunos() {
        return alunoRepository.findAll();
    }

    public Optional<Aluno> getAlunoById(UUID id) {
        return alunoRepository.findById(id);
    }

    public Aluno createAluno(AlunoDTO alunoDTO) {
        Aluno aluno = new Aluno();
        String encryptedPassword = new BCryptPasswordEncoder().encode(alunoDTO.getPassword());

        aluno.setNome(alunoDTO.getNome());
        aluno.setRole(alunoDTO.getRole());
        aluno.setTelefone(alunoDTO.getTelefone());
        aluno.setEmail(alunoDTO.getEmail());
        aluno.setPassword(encryptedPassword);
        aluno.setTurma(alunoDTO.getTurmaId() == null ? null : turmaRepository.findById(alunoDTO.getTurmaId()).orElse(null));
        aluno.setDocumentos(documentoRepository.findAllById(alunoDTO.getDocumentosIds()));
        aluno.setPlano(alunoDTO.getPlanoId() == null ? null : planoRepository.findById(alunoDTO.getPlanoId()).orElse(null));
        return alunoRepository.save(aluno);
    }

    public Aluno updateAluno(UUID id, AlunoDTO alunoDTO) {
        Optional<Aluno> optionalAluno = alunoRepository.findById(id);
        if (optionalAluno.isPresent()) {
            String encryptedPassword = new BCryptPasswordEncoder().encode(alunoDTO.getPassword());

            Aluno aluno = optionalAluno.get();
            aluno.setNome(alunoDTO.getNome());
            aluno.setRole(alunoDTO.getRole());
            aluno.setTelefone(alunoDTO.getTelefone());
            aluno.setEmail(alunoDTO.getEmail());
            aluno.setPassword(encryptedPassword);
            aluno.setTurma(turmaRepository.findById(alunoDTO.getTurmaId()).orElse(null));
            aluno.setDocumentos(documentoRepository.findAllById(alunoDTO.getDocumentosIds()));
            aluno.setPlano(planoRepository.findById(alunoDTO.getPlanoId()).orElse(null));
            return alunoRepository.save(aluno);
        }
        return null;
    }

    public void deleteAluno(UUID id) {
        alunoRepository.deleteById(id);
    }
}