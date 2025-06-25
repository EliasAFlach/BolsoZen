package com.eliasflach.bolsozen.controller;

import com.eliasflach.bolsozen.model.entity.Transacao;
import com.eliasflach.bolsozen.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @PostMapping
    public ResponseEntity<Transacao> criarTransacao(@RequestBody Transacao transacao) {
        String idUsuarioAutenticado = "ID_DO_USUARIO_LOGADO";
        transacao.setIdUsuario(idUsuarioAutenticado);
        Transacao novaTransacao = transacaoService.createTransacao(transacao);
        return new ResponseEntity<>(novaTransacao, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Transacao>> buscarTransacoesDoUsuario() {
        String idUsuarioAutenticado = "ID_DO_USUARIO_LOGADO";
        List<Transacao> transacoes = transacaoService.findTransacoesByUsuario(idUsuarioAutenticado);
        return ResponseEntity.ok(transacoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transacao> buscarTransacaoPorId(@PathVariable String id) {
        Transacao transacao = transacaoService.findTransacaoById(id);
        return ResponseEntity.ok(transacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transacao> atualizarTransacao(@PathVariable String id, @RequestBody Transacao transacaoDetails) {
        Transacao transacaoAtualizada = transacaoService.updateTransacao(id, transacaoDetails);
        return ResponseEntity.ok(transacaoAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTransacao(@PathVariable String id) {
        transacaoService.deleteTransacao(id);
        return ResponseEntity.noContent().build();
    }
}