package com.eliasflach.bolsozen.service.impl;

import com.eliasflach.bolsozen.Dto.UsuarioResponseDTO;
import com.eliasflach.bolsozen.exceptions.BusinessException;
import com.eliasflach.bolsozen.exceptions.ResourceNotFoundException;
import com.eliasflach.bolsozen.model.entity.Usuario;
import com.eliasflach.bolsozen.repository.*;
import com.eliasflach.bolsozen.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UsuarioResponseDTO createUser(Usuario usuario) {
        usuarioRepository.findByEmailAndDeletadoEmIsNull(usuario.getEmail()).ifPresent(u -> {
            throw new BusinessException("Este e-mail já está em uso.");
        });

        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        usuario.setDeletadoEm(null);

        Usuario novoUsuario = usuarioRepository.save(usuario);
        return mapToDto(novoUsuario);
    }

    @Override
    @Transactional
    public void deleteUser(String id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o id: " + id));

        if (usuario.getDeletadoEm() == null) {
            usuario.setDeletadoEm(LocalDateTime.now());

            String emailAntigo = usuario.getEmail();
            String emailNeutralizado = "deleted_" + System.currentTimeMillis() + "_" + emailAntigo;
            usuario.setEmail(emailNeutralizado);

            usuarioRepository.save(usuario);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmailAndDeletadoEmIsNull(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado ou inativo: " + email));

        return new User(
                usuario.getEmail(),
                usuario.getSenha(),
                new ArrayList<>()
        );
    }

    @Override
    public List<UsuarioResponseDTO> findAllUsers() {
        return usuarioRepository.findAll().stream()
                .filter(u -> u.getDeletadoEm() == null)
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioResponseDTO findUserById(String id) {
        Usuario usuario = usuarioRepository.findById(id)
                .filter(u -> u.getDeletadoEm() == null)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado ou inativo: " + id));
        return mapToDto(usuario);
    }

    private UsuarioResponseDTO mapToDto(Usuario usuario) {
        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        return dto;
    }
}