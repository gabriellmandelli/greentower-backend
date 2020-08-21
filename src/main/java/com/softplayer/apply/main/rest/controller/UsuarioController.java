package com.softplayer.apply.main.rest.controller;

import com.softplayer.apply.main.domain.entity.Usuario;
import com.softplayer.apply.main.rest.dto.UsuarioUpdateDTO;
import com.softplayer.apply.main.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @PostMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Usuario> save(@RequestBody Usuario usuario){
        return ResponseEntity.ok(usuarioService.save(usuario));
    }

    @PutMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Usuario> update(@RequestBody UsuarioUpdateDTO usuarioUpdate){
        return ResponseEntity.ok(usuarioService.update(usuarioUpdate));
    }
}
