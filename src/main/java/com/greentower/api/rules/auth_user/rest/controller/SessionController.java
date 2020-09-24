package com.greentower.api.rules.auth_user.rest.controller;

import com.greentower.api.core.security.JwtTokenProvider;
import com.greentower.api.rules.auth_user.domain.entity.Usuario;
import com.greentower.api.rules.auth_user.rest.dto.CredencialLoginDTO;
import com.greentower.api.rules.auth_user.rest.dto.TokenRetornoDTO;
import com.greentower.api.rules.auth_user.service.impl.JwtUserDetailsService;
import com.greentower.api.core.util.MapperUtil;
import com.greentower.api.rules.auth_user.rest.dto.UsuarioSessionDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
@Api(value = "Api Rest Autenticação")
@CrossOrigin(value = "*")
public class SessionController {

    private final MapperUtil modelMapper;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtUserDetailsService usuarioService;

    @Autowired
    public SessionController(MapperUtil modelMapper, JwtUserDetailsService usuarioService, JwtTokenProvider jwtTokenProvider){
        this.modelMapper = modelMapper;
        this.usuarioService = usuarioService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping(value = "/sessions", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Realiza login na sessão")
    public ResponseEntity<TokenRetornoDTO> login(@RequestBody CredencialLoginDTO credencialLogin){
        User userDetails = usuarioService.usuarioAuthenticate(credencialLogin);
        Usuario usuario = usuarioService.findByEmail(credencialLogin.getEmail());
        TokenRetornoDTO tokenRetornoDTO = new TokenRetornoDTO(jwtTokenProvider.generateTokenByUsuario(userDetails), modelMapper.mapTo(usuario, UsuarioSessionDTO.class));
        return ResponseEntity.ok(tokenRetornoDTO);
    }
}
