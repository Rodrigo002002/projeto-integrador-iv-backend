package com.apiathletevision.apiathletevision.services;

import com.apiathletevision.apiathletevision.dtos.PagamentoDTO;
import com.apiathletevision.apiathletevision.entities.Pagamento;
import com.apiathletevision.apiathletevision.repositories.PagamentoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;

    private final ModelMapper modelMapper;

    public List<PagamentoDTO> getAllPagamentos() {
        return pagamentoRepository.findAll().stream().map(pagamento -> modelMapper.map(pagamento, PagamentoDTO.class)).toList();
    }

    public Optional<PagamentoDTO> getPagamentoById(Integer id) {
        Optional<Pagamento> pagamento = pagamentoRepository.findById(id);
        PagamentoDTO pagamentoRequestDTO = modelMapper.map(pagamento, PagamentoDTO.class);
        return Optional.ofNullable(pagamentoRequestDTO);
    }

    public PagamentoDTO createPagamento(PagamentoDTO pagamentoRequestDTO) {
        Pagamento pagamento = new Pagamento();

        pagamento.setDataPagamento(pagamentoRequestDTO.getDataPagamento());
        pagamento.setDataPrazo(pagamentoRequestDTO.getDataPrazo());
        pagamento.setValor(pagamentoRequestDTO.getValor());
        pagamento.setPago(pagamentoRequestDTO.getPago());

        pagamento = pagamentoRepository.save(pagamento);

        return modelMapper.map(pagamento, PagamentoDTO.class);
    }

    public PagamentoDTO updatePagamento(Integer id, PagamentoDTO pagamentoRequestDTO) {
        Optional<Pagamento> optionalPagamento = pagamentoRepository.findById(id);

        if (optionalPagamento.isPresent()) {

            Pagamento pagamento = optionalPagamento.get();
            pagamento.setId(id);
            pagamento.setDataPagamento(pagamentoRequestDTO.getDataPagamento());
            pagamento.setDataPrazo(pagamentoRequestDTO.getDataPrazo());
            pagamento.setValor(pagamentoRequestDTO.getValor());
            pagamento.setPago(pagamentoRequestDTO.getPago());

            pagamento = pagamentoRepository.save(pagamento);

            return modelMapper.map(pagamento, PagamentoDTO.class);
        }
        return null;
    }

    public void deletePagamento(Integer id) {
        pagamentoRepository.deleteById(id);
    }
}