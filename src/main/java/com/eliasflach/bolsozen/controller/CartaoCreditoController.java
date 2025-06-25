package com.eliasflach.bolsozen.controller;

import com.eliasflach.bolsozen.model.entity.CartaoCredito;
import com.eliasflach.bolsozen.service.CartaoCreditoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cartoes")
public class CartaoCreditoController {

    @Autowired
    private CartaoCreditoService cartaoCreditoService;

    @PostMapping
    public ResponseEntity<CartaoCredito> criarCartaoCredito(@RequestBody CartaoCredito cartaoCredito) {
        String idUsuarioAutenticado = "ID_DO_USUARIO_LOGADO";
        cartaoCredito.setIdUsuario(idUsuarioAutenticado);
        CartaoCredito novoCartao = cartaoCreditoService.createCartaoCredito(cartaoCredito);
        return new ResponseEntity<>(novoCartao, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CartaoCredito>> buscarCartoesDoUsuario() {
        String idUsuarioAutenticado = "ID_DO_USUARIO_LOGADO";
        List<CartaoCredito> cartoes = cartaoCreditoService.findCartaoCreditoByUsuario(idUsuarioAutenticado);
        return ResponseEntity.ok(cartoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartaoCredito> buscarCartaoPorId(@PathVariable String id) {
        CartaoCredito cartao = cartaoCreditoService.findCartaoCreditoById(id);
        return ResponseEntity.ok(cartao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartaoCredito> atualizarCartaoCredito(@PathVariable String id, @RequestBody CartaoCredito cartaoDetails) {
        CartaoCredito cartaoAtualizado = cartaoCreditoService.updateCartaoCredito(id, cartaoDetails);
        return ResponseEntity.ok(cartaoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCartaoCredito(@PathVariable String id) {
        cartaoCreditoService.deleteCartaoCredito(id);
        return ResponseEntity.noContent().build();
    }
}