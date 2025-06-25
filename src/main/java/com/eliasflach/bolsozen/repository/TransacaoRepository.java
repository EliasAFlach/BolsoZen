package com.eliasflach.bolsozen.repository;

import com.eliasflach.bolsozen.model.entity.Transacao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoRepository extends MongoRepository<Transacao, String> {
    List<Transacao> findByIdUsuario(String idUsuario);
}