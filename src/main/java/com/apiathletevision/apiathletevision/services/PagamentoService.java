package com.apiathletevision.apiathletevision.services;

import com.apiathletevision.apiathletevision.dtos.PagamentoDTO;
import com.apiathletevision.apiathletevision.entities.Pagamento;
import com.apiathletevision.apiathletevision.repositories.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    public List<Pagamento> getAllPagamentos() {
        return pagamentoRepository.findAll();
    }

    public Optional<Pagamento> getPagamentoById(Integer id) {
        return pagamentoRepository.findById(id);
    }

    public Pagamento createPagamento(PagamentoDTO pagamentoDTO) {
        Pagamento pagamento = new Pagamento();
        pagamento.setDataPagamento(pagamentoDTO.getDataPagamento());
        pagamento.setDataPrazo(pagamentoDTO.getDataPrazo());
        pagamento.setValor(pagamentoDTO.getValor());
        pagamento.setPago(pagamentoDTO.getPago());
        return pagamentoRepository.save(pagamento);
    }

    public Pagamento updatePagamento(Integer id, PagamentoDTO pagamentoDTO) {
        Optional<Pagamento> optionalPagamento = pagamentoRepository.findById(id);
        if (optionalPagamento.isPresent()) {
            Pagamento pagamento = optionalPagamento.get();
            pagamento.setDataPagamento(pagamentoDTO.getDataPagamento());
            pagamento.setDataPrazo(pagamentoDTO.getDataPrazo());
            pagamento.setValor(pagamentoDTO.getValor());
            pagamento.setPago(pagamentoDTO.getPago());
            return pagamentoRepository.save(pagamento);
        }
        return null;
    }

    public void deletePagamento(Integer id) {
        pagamentoRepository.deleteById(id);
    }
}