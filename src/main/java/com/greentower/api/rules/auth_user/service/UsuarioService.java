package com.greentower.api.rules.auth_user.service;

import com.greentower.api.rules.auth_user.domain.entity.Usuario;
import com.greentower.api.rules.auth_user.rest.dto.UsuarioUpdateDTO;

import java.util.List;
import java.util.UUID;

public interface UsuarioService {
    Usuario save(Usuario usuario);

    Usuario update(UsuarioUpdateDTO usuario);

    void delete(UUID usuario);

    void deleteAll();

    List<Usuario> findAll();

    Usuario findByEmail(String email);
}
