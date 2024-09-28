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
        ResponsavelDTO responsavelRequestDTO = modelMapper.map(responsavel, ResponsavelDTO.class);
        return Optional.ofNullable(responsavelRequestDTO);
    }

    public ResponsavelDTO createResponsavel(ResponsavelDTO responsavelRequestDTO) {
        Responsavel responsavel = new Responsavel();
        String encryptedPassword = new BCryptPasswordEncoder().encode(responsavelRequestDTO.getPassword());

        responsavel.setNome(responsavelRequestDTO.getNome());
        responsavel.setRole(responsavelRequestDTO.getRole());
        responsavel.setTelefone(responsavelRequestDTO.getTelefone());
        responsavel.setEmail(responsavelRequestDTO.getEmail());
        responsavel.setPassword(encryptedPassword);
        responsavel.setAlunos(alunoRepository.findAllById(responsavelRequestDTO.getAlunosIds()));
        responsavel.setRg(responsavelRequestDTO.getRg());
        responsavel.setCpf(responsavelRequestDTO.getCpf());

        responsavel = responsavelRepository.save(responsavel);

        return modelMapper.map(responsavel, ResponsavelDTO.class);
    }

    public ResponsavelDTO updateResponsavel(UUID id, ResponsavelDTO responsavelRequestDTO) {
        Optional<Responsavel> optionalResponsavel = responsavelRepository.findById(id);
        if (optionalResponsavel.isPresent()) {
            Responsavel responsavel = optionalResponsavel.get();

            String encryptedPassword = new BCryptPasswordEncoder().encode(responsavelRequestDTO.getPassword());

            responsavel.setId(id);
            responsavel.setNome(responsavelRequestDTO.getNome());
            responsavel.setRole(responsavelRequestDTO.getRole());
            responsavel.setTelefone(responsavelRequestDTO.getTelefone());
            responsavel.setEmail(responsavelRequestDTO.getEmail());
            responsavel.setPassword(encryptedPassword);
            responsavel.setAlunos(alunoRepository.findAllById(responsavelRequestDTO.getAlunosIds()));
            responsavel.setRg(responsavelRequestDTO.getRg());
            responsavel.setCpf(responsavelRequestDTO.getCpf());

            responsavel = responsavelRepository.save(responsavel);

            return modelMapper.map(responsavel, ResponsavelDTO.class);
        }
        return null;
    }

    public void deleteResponsavel(UUID id) {
        responsavelRepository.deleteById(id);
    }
}