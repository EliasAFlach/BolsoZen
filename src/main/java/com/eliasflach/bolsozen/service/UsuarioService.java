package com.eliasflach.bolsozen.service;


import com.eliasflach.bolsozen.Dto.UsuarioResponseDTO;
import com.eliasflach.bolsozen.model.entity.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UsuarioService extends UserDetailsService {
    UsuarioResponseDTO createUser(Usuario usuario);
    List<UsuarioResponseDTO> findAllUsers();
    UsuarioResponseDTO findUserById(String id);
    void deleteUser(String id);
}