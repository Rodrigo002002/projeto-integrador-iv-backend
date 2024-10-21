package com.apiathletevision.apiathletevision.services.impl;

import com.apiathletevision.apiathletevision.dtos.entities.UsuarioDTO;
import com.apiathletevision.apiathletevision.dtos.response.PageDTO;
import com.apiathletevision.apiathletevision.dtos.select2.Select2OptionsDTO;
import com.apiathletevision.apiathletevision.entities.Usuario;
import com.apiathletevision.apiathletevision.exeptions.BadRequestException;
import com.apiathletevision.apiathletevision.mappers.UsuarioMapper;
import com.apiathletevision.apiathletevision.repositories.UsuarioRepository;
import com.apiathletevision.apiathletevision.services.UsuarioService;
import com.apiathletevision.apiathletevision.services.specifications.UsuarioSpecification;
import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    @Override
    public PageDTO<Usuario, UsuarioDTO> findAll(int pageNo, int pageSize, String search, Boolean status) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("nome"));

        Specification<Usuario> specification = UsuarioSpecification.getFilters(search, status);

        Page<Usuario> analistaPage = usuarioRepository.findAll(specification, pageable);

        return new PageDTO<>(analistaPage, usuarioMapper::toDto);
    }

    @Override
    @Transactional
    public UsuarioDTO create(UsuarioDTO usuarioDTO) {

        if (usuarioRepository.findByLogin(usuarioDTO.getLogin()) != null) {
            throw new IllegalArgumentException("Login em uso");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(usuarioDTO.getPassword());

        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        usuario.setPassword(encryptedPassword);
        usuario.setStatus(true);

        Usuario savedUser = usuarioRepository.save(usuario);

        return usuarioMapper.toDto(savedUser);
    }

    @Override
    public UsuarioDTO update(UsuarioDTO usuarioDTO) throws BadRequestException {
        Usuario usuario = getUsuario(usuarioDTO.getId());

        String encryptedPassword = new BCryptPasswordEncoder().encode(usuarioDTO.getPassword());

        usuario = usuarioMapper.partialUpdate(usuarioDTO, usuario);
        usuario.setPassword(encryptedPassword);
        usuarioRepository.save(usuario);

        return usuarioMapper.toDto(usuario);
    }

    @Override
    public UsuarioDTO findById(UUID id) throws BadRequestException {
        Usuario usuario = getUsuario(id);

        return usuarioMapper.toDto(usuario);
    }

    @Override
    public void delete(UUID id) throws BadRequestException {
        Usuario usuario = getUsuario(id);
        if (!usuario.getStatus()) {
            usuarioRepository.delete(usuario);
        }
        throw new BadRequestException("Usuário ainda ativo");
    }

    @Override
    public void changeStatus(UUID id, boolean status) {
        Optional<Usuario> optionalUsuario = this.usuarioRepository.findById(id);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            usuario.setStatus(status);

            this.usuarioRepository.save(usuario);
        }
    }

    @Override
    public List<Select2OptionsDTO> findAllToSelect2(String searchTerm) {

        List<Usuario> list;

        if (StringUtils.isBlank(searchTerm)) {
            list = usuarioRepository.findAllByStatusIsTrue();
        } else {
            list = usuarioRepository.findByStatusIsTrueAndNomeContainingIgnoreCaseOrderByNome(searchTerm);
        }

        return list
                .stream()
                .map(data -> new Select2OptionsDTO(data.getId(), data.getNome()))
                .collect(Collectors.toList());

    }

    private Usuario getUsuario(UUID id) throws BadRequestException {
        return usuarioRepository.findById(id).orElseThrow(()
                -> new BadRequestException("Usuário com o ID: " + id + " não encontrado"));
    }
}