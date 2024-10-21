package com.apiathletevision.apiathletevision.controllers;

import com.apiathletevision.apiathletevision.dtos.entities.ModalidadeDTO;
import com.apiathletevision.apiathletevision.services.impl.ModalidadeServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Modalidades")
@RestController
@RequestMapping("${api-prefix}/modalidades")
@RequiredArgsConstructor
public class ModalidadeController {

    private final ModalidadeServiceImpl modalidadeService;
}