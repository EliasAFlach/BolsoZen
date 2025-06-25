package com.eliasflach.bolsozen.repository;

import com.eliasflach.bolsozen.model.entity.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    Optional<Usuario> findByEmailAndDeletadoEmIsNull(String email);
    Optional<Usuario> findByEmail(String email);
}