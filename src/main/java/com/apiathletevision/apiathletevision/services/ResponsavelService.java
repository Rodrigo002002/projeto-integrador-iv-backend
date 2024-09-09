package com.apiathletevision.apiathletevision.services;

import com.apiathletevision.apiathletevision.dtos.ResponsavelDTO;
import com.apiathletevision.apiathletevision.entities.Responsavel;
import com.apiathletevision.apiathletevision.repositories.ResponsavelRepository;
import com.apiathletevision.apiathletevision.repositories.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ResponsavelService {

    @Autowired
    private ResponsavelRepository responsavelRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    public List<Responsavel> getAllResponsaveis() {
        return responsavelRepository.findAll();
    }

    public Optional<Responsavel> getResponsavelById(UUID id) {
        return responsavelRepository.findById(id);
    }

    public Responsavel createResponsavel(ResponsavelDTO responsavelDTO) {
        Responsavel responsavel = new Responsavel();
        String encryptedPassword = new BCryptPasswordEncoder().encode(responsavelDTO.getPassword());

        responsavel.setNome(responsavelDTO.getNome());
        responsavel.setRole(responsavelDTO.getRole());
        responsavel.setTelefone(responsavelDTO.getTelefone());
        responsavel.setEmail(responsavelDTO.getEmail());
        responsavel.setPassword(encryptedPassword);
        responsavel.setAlunos(alunoRepository.findAllById(responsavelDTO.getAlunosIds()));
        return responsavelRepository.save(responsavel);
    }

    public Responsavel updateResponsavel(UUID id, ResponsavelDTO responsavelDTO) {
        Optional<Responsavel> optionalResponsavel = responsavelRepository.findById(id);
        if (optionalResponsavel.isPresent()) {
            Responsavel responsavel = optionalResponsavel.get();

            String encryptedPassword = new BCryptPasswordEncoder().encode(responsavelDTO.getPassword());

            responsavel.setNome(responsavelDTO.getNome());
            responsavel.setRole(responsavelDTO.getRole());
            responsavel.setTelefone(responsavelDTO.getTelefone());
            responsavel.setEmail(responsavelDTO.getEmail());
            responsavel.setPassword(encryptedPassword);
            responsavel.setAlunos(alunoRepository.findAllById(responsavelDTO.getAlunosIds()));
            return responsavelRepository.save(responsavel);
        }
        return null;
    }

    public void deleteResponsavel(UUID id) {
        responsavelRepository.deleteById(id);
    }
}