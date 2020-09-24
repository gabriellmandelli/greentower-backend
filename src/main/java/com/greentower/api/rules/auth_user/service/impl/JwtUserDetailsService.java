package com.greentower.api.rules.auth_user.service.impl;

import com.greentower.api.rules.auth_user.domain.entity.Usuario;
import com.greentower.api.rules.auth_user.domain.repository.UsuarioRepository;
import com.greentower.api.rules.auth_user.rest.dto.CredencialLoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public JwtUserDetailsService(PasswordEncoder passwordEncoder, UsuarioRepository usuarioRepository) {
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario findByEmail(String email){
        return usuarioRepository.findByemail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario ou senha invalida"));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuarioDb = this.findByEmail(email);

        return new User(usuarioDb.getEmail(), usuarioDb.getPassword(), AuthorityUtils.createAuthorityList("ROLE_ADMIN"));
    }

    public User usuarioAuthenticate(CredencialLoginDTO credencialLoginDTO) {
        User userDetails = (User) loadUserByUsername(credencialLoginDTO.getEmail());
        if (passwordEncoder.matches(credencialLoginDTO.getPassword(), userDetails.getPassword())){
            return userDetails;
        }else{
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário ou senha inválida");
        }
    }
}
