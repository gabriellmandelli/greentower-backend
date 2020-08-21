package com.softplayer.apply.main.rest.controller;

import com.softplayer.apply.main.domain.entity.Usuario;
import com.softplayer.apply.main.rest.dto.CredencialLoginDTO;
import com.softplayer.apply.main.rest.dto.TokenRetornoDTO;
import com.softplayer.apply.main.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class AuthController {

    private final UsuarioService usuarioService;

    @Autowired
    public AuthController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @PostMapping(value = "/sessions", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TokenRetornoDTO> login(@RequestBody CredencialLoginDTO credencialLogin){

        usuarioService.usuarioAuthenticate(credencialLogin);

        Usuario usuario = usuarioService.findByEmail(credencialLogin.getEmail());
        usuario.setPassword("");

        TokenRetornoDTO tokenRetornoDTO = new TokenRetornoDTO("", usuario);

        return ResponseEntity.ok(tokenRetornoDTO);
    }
}
