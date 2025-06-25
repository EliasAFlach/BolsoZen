package com.eliasflach.bolsozen.controller;

import com.eliasflach.bolsozen.model.entity.Categoria;
import com.eliasflach.bolsozen.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<Categoria> criarCategoria(@RequestBody Categoria categoria) {
        String idUsuarioAutenticado = "ID_DO_USUARIO_LOGADO";
        categoria.setIdUsuario(idUsuarioAutenticado);
        Categoria novaCategoria = categoriaService.createCategoria(categoria);
        return new ResponseEntity<>(novaCategoria, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> buscarCategoriasDoUsuario() {
        String idUsuarioAutenticado = "ID_DO_USUARIO_LOGADO";
        List<Categoria> categorias = categoriaService.findCategoriasByUsuario(idUsuarioAutenticado);
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarCategoriaPorId(@PathVariable String id) {
        Categoria categoria = categoriaService.findCategoriaById(id);
        return ResponseEntity.ok(categoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizarCategoria(@PathVariable String id, @RequestBody Categoria categoriaDetails) {
        Categoria categoriaAtualizada = categoriaService.updateCategoria(id, categoriaDetails);
        return ResponseEntity.ok(categoriaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable String id) {
        categoriaService.deleteCategoria(id);
        return ResponseEntity.noContent().build();
    }
}