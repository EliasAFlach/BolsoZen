package com.eliasflach.bolsozen.service;

import com.eliasflach.bolsozen.model.entity.CartaoCredito;

import java.util.List;

public interface CartaoCreditoService {
    CartaoCredito createCartaoCredito(CartaoCredito cartaoCredito);
    CartaoCredito updateCartaoCredito(String id, CartaoCredito cartaoCredito);
    List<CartaoCredito> findCartaoCreditoByUsuario(String idUsuario);
    CartaoCredito findCartaoCreditoById(String id);
    void deleteCartaoCredito(String id);
}