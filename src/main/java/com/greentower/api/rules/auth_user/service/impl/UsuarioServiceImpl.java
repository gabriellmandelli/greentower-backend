package com.greentower.api.rules.auth_user.service.impl;

import com.greentower.api.rules.auth_user.domain.entity.Usuario;
import com.greentower.api.rules.auth_user.domain.repository.UsuarioRepository;
import com.greentower.api.rules.auth_user.rest.dto.UsuarioUpdateDTO;
import com.greentower.api.rules.auth_user.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder){
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Usuario save(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario update(UsuarioUpdateDTO usuarioUpdateDTO) {

        Usuario usuarioDb = findByEmail(usuarioUpdateDTO.getEmail());

        boolean lbAtualizaPassword = (usuarioUpdateDTO.getPassword() != null && usuarioUpdateDTO.getOldPassword() != null && usuarioUpdateDTO.getConfirmPassword() != null);

        if (lbAtualizaPassword){
            lbAtualizaPassword = usuarioUpdateDTO.getConfirmPassword().equals(usuarioUpdateDTO.getPassword());
            if (lbAtualizaPassword && passwordEncoder.matches(usuarioUpdateDTO.getOldPassword(), usuarioDb.getPassword())){
                usuarioDb.setPassword(passwordEncoder.encode(usuarioUpdateDTO.getPassword()));
            }
        }

        usuarioDb.setEmail(usuarioUpdateDTO.getEmail());
        usuarioDb.setName(usuarioUpdateDTO.getName());
        usuarioDb = usuarioRepository.save(usuarioDb);
        usuarioDb.setPassword("");

        return usuarioDb;
    }

    @Override
    public void delete(UUID idUsuario) {
        usuarioRepository.deleteById(idUsuario);
    }

    @Override
    public void deleteAll() {
        usuarioRepository.deleteAll();
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario findByEmail(String emailUsuario) {
        return usuarioRepository.findByemail(emailUsuario).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario não existe na base de dados"));
    }
}
