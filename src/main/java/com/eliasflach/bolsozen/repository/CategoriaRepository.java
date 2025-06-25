package com.eliasflach.bolsozen.repository;

import com.eliasflach.bolsozen.model.entity.Categoria;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository extends MongoRepository<Categoria, String> {
    List<Categoria> findByIdUsuario(String idUsuario);
    Optional<Categoria> findByNomeAndIdUsuario(String nome, String idUsuario);
}