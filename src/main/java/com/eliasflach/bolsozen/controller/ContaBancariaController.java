package com.eliasflach.bolsozen.controller;

import com.eliasflach.bolsozen.model.entity.ContaBancaria;
import com.eliasflach.bolsozen.service.ContaBancariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contas")
public class ContaBancariaController {

    @Autowired
    private ContaBancariaService contaBancariaService;

    @PostMapping
    public ResponseEntity<ContaBancaria> criarContaBancaria(@RequestBody ContaBancaria contaBancaria) {
        String idUsuarioAutenticado = "ID_DO_USUARIO_LOGADO";
        contaBancaria.setIdUsuario(idUsuarioAutenticado);
        ContaBancaria novaConta = contaBancariaService.createContaBancaria(contaBancaria);
        return new ResponseEntity<>(novaConta, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ContaBancaria>> buscarContasDoUsuario() {
        String idUsuarioAutenticado = "ID_DO_USUARIO_LOGADO";
        List<ContaBancaria> contas = contaBancariaService.findContasBancariasByUsuario(idUsuarioAutenticado);
        return ResponseEntity.ok(contas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContaBancaria> buscarContaPorId(@PathVariable String id) {
        ContaBancaria conta = contaBancariaService.findContaBancariaById(id);
        return ResponseEntity.ok(conta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContaBancaria> atualizarContaBancaria(@PathVariable String id, @RequestBody ContaBancaria contaDetails) {
        ContaBancaria contaAtualizada = contaBancariaService.updateContaBancaria(id, contaDetails);
        return ResponseEntity.ok(contaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarContaBancaria(@PathVariable String id) {
        contaBancariaService.deleteContaBancaria(id);
        return ResponseEntity.noContent().build();
    }
}