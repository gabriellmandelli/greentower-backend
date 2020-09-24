package com.greentower.api.rules.auth_user.rest.controller;

import com.greentower.api.rules.auth_user.domain.entity.Usuario;
import com.greentower.api.rules.auth_user.rest.dto.UsuarioDTO;
import com.greentower.api.rules.auth_user.rest.dto.UsuarioUpdateDTO;
import com.greentower.api.rules.auth_user.service.UsuarioService;
import com.greentower.api.core.util.MapperUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Api(value = "Api Rest de Usuários")
@CrossOrigin(value = "*")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final MapperUtil modelMapper;

    @Autowired
    public UsuarioController(UsuarioService usuarioService, MapperUtil modelMapper){
        this.usuarioService = usuarioService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Criação de usuário")
    public ResponseEntity<UsuarioDTO> save(@RequestBody UsuarioDTO usuarioDTO){
        Usuario usuarioDB = modelMapper.mapTo(usuarioDTO, Usuario.class);
        return ResponseEntity.ok(modelMapper.mapTo(usuarioService.save(usuarioDB), UsuarioDTO.class));
    }

    @PutMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Atualização de usuário")
    public ResponseEntity<UsuarioDTO> update(@RequestBody UsuarioUpdateDTO usuarioUpdate){
        return ResponseEntity.ok(modelMapper.mapTo(usuarioService.update(usuarioUpdate), UsuarioDTO.class));
    }
}
