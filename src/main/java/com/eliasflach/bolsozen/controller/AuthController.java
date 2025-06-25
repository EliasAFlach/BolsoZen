package com.eliasflach.bolsozen.controller;

import com.eliasflach.bolsozen.Dto.UsuarioResponseDTO;
import com.eliasflach.bolsozen.model.entity.Usuario;
import com.eliasflach.bolsozen.service.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;

    @PostMapping("/register")
    public ResponseEntity<UsuarioResponseDTO> cadastrarUsuario(@RequestBody Usuario usuario) {
        UsuarioResponseDTO novoUsuario = usuarioServiceImpl.createUser(usuario);
        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
    }
}
