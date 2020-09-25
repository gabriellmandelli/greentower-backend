package com.greentower.api.rules.auth_user.rest.controller;

import com.greentower.api.core.security.JwtTokenProvider;
import com.greentower.api.rules.auth_user.domain.entity.AuthUser;
import com.greentower.api.rules.auth_user.rest.dto.SessionLoginDTO;
import com.greentower.api.rules.auth_user.rest.dto.PayloadSessionDTO;
import com.greentower.api.rules.auth_user.service.impl.JwtUserDetailsService;
import com.greentower.api.core.util.MapperUtil;
import com.greentower.api.rules.auth_user.rest.dto.AuthUserSessionDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
@Api(value = "Api Rest Authentication")
@CrossOrigin(value = "*")
public class SessionController {

    private final MapperUtil modelMapper;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    public SessionController(MapperUtil modelMapper, JwtUserDetailsService jwtUserDetailsService, JwtTokenProvider jwtTokenProvider){
        this.modelMapper = modelMapper;
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping(value = "/sessions", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Session Login")
    public ResponseEntity<PayloadSessionDTO> login(@RequestBody SessionLoginDTO credencialLogin){
        User userDetails = jwtUserDetailsService.AuthUserAuthenticate(credencialLogin);
        AuthUser authUser = jwtUserDetailsService.findByEmail(credencialLogin.getEmail());
        PayloadSessionDTO payloadSessionDTO = new PayloadSessionDTO(jwtTokenProvider.generateTokenByAuthUser(userDetails), modelMapper.mapTo(authUser, AuthUserSessionDTO.class));
        return ResponseEntity.ok(payloadSessionDTO);
    }
}
