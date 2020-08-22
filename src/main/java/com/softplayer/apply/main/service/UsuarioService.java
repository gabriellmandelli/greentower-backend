package com.softplayer.apply.main.service;

import com.softplayer.apply.main.domain.entity.Usuario;
import com.softplayer.apply.main.rest.dto.CredencialLoginDTO;
import com.softplayer.apply.main.rest.dto.UsuarioUpdateDTO;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.UUID;

public interface UsuarioService extends UserDetailsService {
    Usuario save(Usuario usuario);

    Usuario update(UsuarioUpdateDTO usuario);

    void delete(UUID usuario);

    void deleteAll();

    List<Usuario> findAll();

    Usuario findByEmail(String email);

    UserDetails loadUserByUsername(String email);

    User usuarioAuthenticate(CredencialLoginDTO usuario);
}
