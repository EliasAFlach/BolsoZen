package com.eliasflach.bolsozen.controller;

import com.eliasflach.bolsozen.Dto.UsuarioResponseDTO;
import com.eliasflach.bolsozen.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> buscarTodosUsuarios() {
        List<UsuarioResponseDTO> usuarios = usuarioService.findAllUsers();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarUsuarioPorId(@PathVariable String id) {
        UsuarioResponseDTO usuario = usuarioService.findUserById(id);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable String id) {
        usuarioService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}