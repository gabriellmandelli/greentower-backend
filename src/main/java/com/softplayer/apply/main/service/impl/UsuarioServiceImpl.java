package com.softplayer.apply.main.service.impl;

import com.softplayer.apply.main.domain.entity.Usuario;
import com.softplayer.apply.main.domain.repository.UsuarioRepository;
import com.softplayer.apply.main.rest.dto.CredencialLoginDTO;
import com.softplayer.apply.main.rest.dto.UsuarioUpdateDTO;
import com.softplayer.apply.main.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

        Boolean lbAtualizaPassword = (usuarioUpdateDTO.getPassword() != null && usuarioUpdateDTO.getOldPassword() != null && usuarioUpdateDTO.getConfirmPassword() != null);

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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuarioDb = usuarioRepository.findByemail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario ou senha invalida"));

        return new User(usuarioDb.getEmail(), usuarioDb.getPassword(), AuthorityUtils.createAuthorityList("ROLE_ADMIN"));
    }

    @Override
    public User usuarioAuthenticate(CredencialLoginDTO usuario) {
        User user = (User) loadUserByUsername(usuario.getEmail());
        if (passwordEncoder.matches(usuario.getPassword(), user.getPassword())){
            return user;
        }else{
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario ou senha inválida");
        }
    }
}
