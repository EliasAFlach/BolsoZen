package com.eliasflach.bolsozen.repository;

import com.eliasflach.bolsozen.model.entity.CartaoCredito;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CartaoCreditoRepository extends MongoRepository<CartaoCredito, String> {
    List<CartaoCredito> findByIdUsuario(String idUsuario);
    Optional<CartaoCredito> findByNomeAndIdUsuario(String nome, String idUsuario);
}