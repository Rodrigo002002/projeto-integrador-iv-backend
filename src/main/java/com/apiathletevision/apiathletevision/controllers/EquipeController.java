package com.apiathletevision.apiathletevision.controllers;

import com.apiathletevision.apiathletevision.dtos.entities.EquipeDTO;
import com.apiathletevision.apiathletevision.services.impl.EquipeServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Equipes")
@RestController
@RequestMapping("${api-prefix}/equipes")
@RequiredArgsConstructor
public class EquipeController {

    private final EquipeServiceImpl equipeService;
}