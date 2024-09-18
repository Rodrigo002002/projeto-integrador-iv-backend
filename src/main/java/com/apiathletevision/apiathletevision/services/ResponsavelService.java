package com.apiathletevision.apiathletevision.services;

import com.apiathletevision.apiathletevision.dtos.ResponsavelDTO;
import com.apiathletevision.apiathletevision.entities.Responsavel;
import com.apiathletevision.apiathletevision.repositories.AlunoRepository;
import com.apiathletevision.apiathletevision.repositories.ResponsavelRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ResponsavelService {

    private final ResponsavelRepository responsavelRepository;
    private final AlunoRepository alunoRepository;
    private final ModelMapper modelMapper;

    public List<ResponsavelDTO> getAllResponsaveis() {
        return responsavelRepository.findAll().stream().map(responsavel -> modelMapper.map(responsavel, ResponsavelDTO.class)).toList();
    }

    public Optional<ResponsavelDTO> getResponsavelById(UUID id) {
        Optional<Responsavel> responsavel = responsavelRepository.findById(id);
        ResponsavelDTO responsavelDTO = modelMapper.map(responsavel, ResponsavelDTO.class);
        return Optional.ofNullable(responsavelDTO);
    }

    public ResponsavelDTO createResponsavel(ResponsavelDTO responsavelDTO) {
        Responsavel responsavel = new Responsavel();
        String encryptedPassword = new BCryptPasswordEncoder().encode(responsavelDTO.getPassword());

        responsavel.setNome(responsavelDTO.getNome());
        responsavel.setRole(responsavelDTO.getRole());
        responsavel.setTelefone(responsavelDTO.getTelefone());
        responsavel.setEmail(responsavelDTO.getEmail());
        responsavel.setPassword(encryptedPassword);
        responsavel.setAlunos(alunoRepository.findAllById(responsavelDTO.getAlunosIds()));
        responsavel.setRg(responsavelDTO.getRg());
        responsavel.setCpf(responsavelDTO.getCpf());

        responsavel = responsavelRepository.save(responsavel);

        return modelMapper.map(responsavel, ResponsavelDTO.class);
    }

    public ResponsavelDTO updateResponsavel(UUID id, ResponsavelDTO responsavelDTO) {
        Optional<Responsavel> optionalResponsavel = responsavelRepository.findById(id);
        if (optionalResponsavel.isPresent()) {
            Responsavel responsavel = optionalResponsavel.get();

            String encryptedPassword = new BCryptPasswordEncoder().encode(responsavelDTO.getPassword());

            responsavel.setId(id);
            responsavel.setNome(responsavelDTO.getNome());
            responsavel.setRole(responsavelDTO.getRole());
            responsavel.setTelefone(responsavelDTO.getTelefone());
            responsavel.setEmail(responsavelDTO.getEmail());
            responsavel.setPassword(encryptedPassword);
            responsavel.setAlunos(alunoRepository.findAllById(responsavelDTO.getAlunosIds()));
            responsavel.setRg(responsavelDTO.getRg());
            responsavel.setCpf(responsavelDTO.getCpf());

            responsavel = responsavelRepository.save(responsavel);

            return modelMapper.map(responsavel, ResponsavelDTO.class);
        }
        return null;
    }

    public void deleteResponsavel(UUID id) {
        responsavelRepository.deleteById(id);
    }
}