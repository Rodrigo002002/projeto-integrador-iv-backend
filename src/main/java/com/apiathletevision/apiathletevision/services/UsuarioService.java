package com.apiathletevision.apiathletevision.services;

import com.apiathletevision.apiathletevision.dtos.entities.UsuarioDTO;
import com.apiathletevision.apiathletevision.dtos.response.PageDTO;
import com.apiathletevision.apiathletevision.dtos.select2.Select2OptionsDTO;
import com.apiathletevision.apiathletevision.entities.Usuario;
import com.apiathletevision.apiathletevision.exeptions.BadRequestException;

import java.util.List;
import java.util.UUID;

public interface UsuarioService {

    PageDTO<Usuario, UsuarioDTO> findAll(int pageNo, int pageSize, String search, Boolean status);
    UsuarioDTO create(UsuarioDTO usuarioDTO);
    UsuarioDTO update(UsuarioDTO usuarioDTO) throws BadRequestException;
    UsuarioDTO findById(UUID id) throws BadRequestException;
    void delete(UUID id) throws BadRequestException;
    void changeStatus(UUID var1, boolean var2);
    List<Select2OptionsDTO> findAllToSelect2(String searchTerm);
}
