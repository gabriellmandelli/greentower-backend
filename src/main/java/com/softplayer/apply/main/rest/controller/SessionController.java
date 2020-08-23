package com.softplayer.apply.main.rest.controller;

import com.softplayer.apply.infrastructure.security.JwtTokenProvider;
import com.softplayer.apply.infrastructure.util.MapperUtil;
import com.softplayer.apply.main.domain.entity.Usuario;
import com.softplayer.apply.main.rest.dto.CredencialLoginDTO;
import com.softplayer.apply.main.rest.dto.TokenRetornoDTO;
import com.softplayer.apply.main.rest.dto.UsuarioDTO;
import com.softplayer.apply.main.service.UsuarioService;
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

    private final UsuarioService usuarioService;

    private final MapperUtil modelMapper;

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public SessionController(UsuarioService usuarioService, MapperUtil modelMapper, JwtTokenProvider jwtTokenProvider){
        this.usuarioService = usuarioService;
        this.modelMapper = modelMapper;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping(value = "/sessions", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Realiza login na sessão")
    public ResponseEntity<TokenRetornoDTO> login(@RequestBody CredencialLoginDTO credencialLogin){
        User userDetails = usuarioService.usuarioAuthenticate(credencialLogin);
        Usuario usuario = usuarioService.findByEmail(credencialLogin.getEmail());
        usuario.setPassword("");
        TokenRetornoDTO tokenRetornoDTO = new TokenRetornoDTO(jwtTokenProvider.generateTokenByUsuario(userDetails), modelMapper.mapTo(usuario, UsuarioDTO.class));
        return ResponseEntity.ok(tokenRetornoDTO);
    }
}
