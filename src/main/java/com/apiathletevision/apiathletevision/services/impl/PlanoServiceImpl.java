package com.apiathletevision.apiathletevision.services.impl;

import com.apiathletevision.apiathletevision.repositories.ModalidadeRepository;
import com.apiathletevision.apiathletevision.repositories.PagamentoRepository;
import com.apiathletevision.apiathletevision.repositories.PlanoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlanoServiceImpl {

    private final PlanoRepository planoRepository;
    private final ModalidadeRepository modalidadeRepository;
    private final PagamentoRepository pagamentoRepository;

}