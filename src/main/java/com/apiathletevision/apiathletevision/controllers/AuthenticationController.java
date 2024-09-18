package com.apiathletevision.apiathletevision.controllers;

import com.apiathletevision.apiathletevision.dtos.AlunoDTO;
import com.apiathletevision.apiathletevision.dtos.ResponsavelDTO;
import com.apiathletevision.apiathletevision.dtos.auth.AuthenticationDTO;
import com.apiathletevision.apiathletevision.dtos.auth.LoginResponseDTO;
import com.apiathletevision.apiathletevision.dtos.auth.RegisterAlunoDTO;
import com.apiathletevision.apiathletevision.dtos.auth.RegisterResponsavelDTO;
import com.apiathletevision.apiathletevision.enums.UserRole;
import com.apiathletevision.apiathletevision.repositories.UsuarioRepository;
import com.apiathletevision.apiathletevision.services.AlunoService;
import com.apiathletevision.apiathletevision.services.ResponsavelService;
import com.apiathletevision.apiathletevision.services.TokenService;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Auth")
public class AuthenticationController {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    private final AuthenticationManager authenticationManager;

    private final UsuarioRepository usuarioRepository;

    private final AlunoService alunoService;

    private final ResponsavelService responsavelService;

    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());

        var auth = authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((String) data.email());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/registrar/aluno")
    public ResponseEntity alunoRegister(@RequestBody @Valid RegisterAlunoDTO data) {
        if (usuarioRepository.findByEmail(data.email()).isPresent()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        AlunoDTO aluno = new AlunoDTO();
        aluno.setNome(data.nome());
        aluno.setRole(UserRole.ALUNO);
        aluno.setTelefone(data.telefone());
        aluno.setEmail(data.email());
        aluno.setPassword(data.password());
        aluno.setDocumentosIds(data.documentosIds());
        alunoService.createAluno(aluno);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/registrar/responsavel")
    public ResponseEntity responsavelRegister(@RequestBody @Valid RegisterResponsavelDTO data) {
        if (this.usuarioRepository.findByEmail(data.email()).isPresent()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());


        ResponsavelDTO responsavel = new ResponsavelDTO();
        responsavel.setNome(data.nome());
        responsavel.setRole(UserRole.RESPONSAVEL);
        responsavel.setTelefone(data.telefone());
        responsavel.setEmail(data.email());
        responsavel.setPassword(data.password());
        responsavelService.createResponsavel(responsavel);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}