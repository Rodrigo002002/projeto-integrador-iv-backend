package com.apiathletevision.apiathletevision.services.impl;

import com.apiathletevision.apiathletevision.repositories.EquipeRepository;
import com.apiathletevision.apiathletevision.repositories.EventoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventoServiceImpl {

    private final EventoRepository eventoRepository;
    private final EquipeRepository equipeRepository;

}