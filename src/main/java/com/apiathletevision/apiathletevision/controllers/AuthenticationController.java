package com.apiathletevision.apiathletevision.controllers;

import com.apiathletevision.apiathletevision.dtos.AlunoDTO;
import com.apiathletevision.apiathletevision.dtos.AuthenticationDTO;
import com.apiathletevision.apiathletevision.dtos.LoginResponseDTO;
import com.apiathletevision.apiathletevision.dtos.RegistroAlunoDTO;
import com.apiathletevision.apiathletevision.enums.UserRole;
import com.apiathletevision.apiathletevision.repositories.UsuarioRepository;
import com.apiathletevision.apiathletevision.services.AlunoService;
import com.apiathletevision.apiathletevision.services.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    private final AuthenticationManager authenticationManager;

    private final UsuarioRepository usuarioRepository;

    private final AlunoService alunoService;

    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());

        var auth = authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((String) usernamePassword.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/registrar/aluno")
    public ResponseEntity alunoRegister(@RequestBody @Valid RegistroAlunoDTO data) {
        if (usuarioRepository.findByEmail(data.email()) != null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

        AlunoDTO aluno = new AlunoDTO();
        aluno.setNome(data.nome());
        aluno.setRole(UserRole.ALUNO);
        aluno.setTelefone(data.telefone());
        aluno.setEmail(data.email());
        aluno.setPassword(encryptedPassword);
        aluno.setDocumentosIds(data.documentosIds());

        alunoService.createAluno(aluno);

        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @PostMapping("/registrar/responsavel")
//    public ResponseEntity professorRegister(@RequestBody @Valid RegisterDTO data) {
//        if (this.usuarioRepository.findByEmail(data.email()) != null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//
//        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
//
//
//        this.repository.save(newUser);
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}