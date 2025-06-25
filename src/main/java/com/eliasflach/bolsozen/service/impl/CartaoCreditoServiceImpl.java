package com.eliasflach.bolsozen.service.impl;

import com.eliasflach.bolsozen.exceptions.BusinessException;
import com.eliasflach.bolsozen.exceptions.ResourceNotFoundException;
import com.eliasflach.bolsozen.model.entity.CartaoCredito;
import com.eliasflach.bolsozen.repository.CartaoCreditoRepository;
import com.eliasflach.bolsozen.service.CartaoCreditoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartaoCreditoServiceImpl implements CartaoCreditoService {

    @Autowired
    private CartaoCreditoRepository cartaoCreditoRepository;

    @Override
    public CartaoCredito createCartaoCredito(CartaoCredito cartaoCredito) {
        cartaoCreditoRepository.findByNomeAndIdUsuario(cartaoCredito.getNome(), cartaoCredito.getIdUsuario())
                .ifPresent(c -> {
                    throw new BusinessException("Já existe uma conta com este nome.");
                });
        return cartaoCreditoRepository.save(cartaoCredito);
    }

    @Override
    public CartaoCredito updateCartaoCredito(String id, CartaoCredito cartaoCreditoDetails) {
        CartaoCredito cartaoCredito = findCartaoCreditoById(id);

        cartaoCredito.setNome(cartaoCreditoDetails.getNome());
        cartaoCredito.setDiaFechamento(cartaoCreditoDetails.getDiaFechamento());
        cartaoCredito.setDiaVencimento(cartaoCreditoDetails.getDiaVencimento());
        cartaoCredito.setLimite(cartaoCreditoDetails.getLimite());
        return cartaoCreditoRepository.save(cartaoCredito);
    }

    @Override
    public List<CartaoCredito> findCartoesByUsuario(String idUsuario) {
        return cartaoCreditoRepository.findByIdUsuario(idUsuario);
    }

    @Override
    public CartaoCredito findCartaoCreditoById(String id) {
        return cartaoCreditoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CartaoCredito não encontrado com o id: " + id));
    }

    @Override
    public void deleteCartaoCredito(String id) {
        CartaoCredito cartaoCredito = findCartaoCreditoById(id);
        cartaoCreditoRepository.delete(cartaoCredito);
    }
}