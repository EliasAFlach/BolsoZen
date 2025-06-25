package com.eliasflach.bolsozen.service;

import com.eliasflach.bolsozen.model.entity.Categoria;

import java.util.List;

public interface CategoriaService {
    Categoria createCategoria(Categoria categoria);
    Categoria updateCategoria(String id, Categoria categoria);
    List<Categoria> findCategoriasByUsuario(String idUsuario);
    Categoria findCategoriaById(String id);
    void deleteCategoria(String id);
}