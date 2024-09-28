package com.apiathletevision.apiathletevision.services;

import com.apiathletevision.apiathletevision.dtos.responses.UsuarioResponseDTO;
import com.apiathletevision.apiathletevision.entities.Usuario;
import com.apiathletevision.apiathletevision.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsuarioService {

    private final ModelMapper modelMapper;
    private final UsuarioRepository usuarioRepository;

    public List<UsuarioResponseDTO> getAllUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioResponseDTO.class))
                .toList();
    }

    public Optional<UsuarioResponseDTO> getUsuarioById(UUID id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.map(value -> modelMapper.map(value, UsuarioResponseDTO.class));
    }

    public UsuarioResponseDTO createUsuario(UsuarioResponseDTO usuarioDTO) {
        Usuario usuario = new Usuario();

        String encryptedPassword = new BCryptPasswordEncoder().encode(usuarioDTO.getPassword());

        usuario.setNome(usuarioDTO.getNome());
        usuario.setRole(usuarioDTO.getRole());
        usuario.setTelefone(usuarioDTO.getTelefone());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setPassword(encryptedPassword);
        usuario.setRg(usuarioDTO.getRg());
        usuario.setCpf(usuarioDTO.getCpf());

        usuario = usuarioRepository.save(usuario);

        return modelMapper.map(usuario, UsuarioResponseDTO.class);
    }

    public UsuarioResponseDTO updateUsuario(UUID id, UsuarioResponseDTO usuarioDTO) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);

        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();

            String encryptedPassword = new BCryptPasswordEncoder().encode(usuarioDTO.getPassword());

            usuario.setId(id);
            usuario.setNome(usuarioDTO.getNome());
            usuario.setRole(usuarioDTO.getRole());
            usuario.setTelefone(usuarioDTO.getTelefone());
            usuario.setEmail(usuarioDTO.getEmail());
            usuario.setPassword(encryptedPassword);
            usuario.setRg(usuarioDTO.getRg());
            usuario.setCpf(usuarioDTO.getCpf());

            usuario = usuarioRepository.save(usuario);

            return modelMapper.map(usuario, UsuarioResponseDTO.class);
        }
        return null;
    }

    public void deleteUsuario(UUID id) {
        usuarioRepository.deleteById(id);
    }
}