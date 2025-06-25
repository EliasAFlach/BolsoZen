package com.eliasflach.bolsozen.service;

import com.eliasflach.bolsozen.model.entity.Transacao;

import java.util.List;

public interface TransacaoService {
    Transacao createTransacao(Transacao transacao);
    Transacao updateTransacao(String id, Transacao transacao);
    List<Transacao> findTransacoesByUsuario(String idUsuario);
    Transacao findTransacaoById(String id);
    void deleteTransacao(String id);
}