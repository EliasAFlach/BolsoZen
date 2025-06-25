package com.eliasflach.bolsozen.repository;

import com.eliasflach.bolsozen.model.entity.ContaBancaria;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ContaBancariaRepository extends MongoRepository<ContaBancaria, String> {
    List<ContaBancaria> findByIdUsuario(String idUsuario);
    Optional<ContaBancaria> findByNomeAndIdUsuario(String nome, String idUsuario);
}