package com.apiathletevision.apiathletevision.controllers;

import com.apiathletevision.apiathletevision.dtos.entities.PagamentoDTO;
import com.apiathletevision.apiathletevision.services.impl.PagamentoServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Pagamentos")
@RestController
@RequestMapping("${api-prefix}/pagamentos")
@RequiredArgsConstructor
public class PagamentoController {

    private final PagamentoServiceImpl pagamentoService;
}