package com.greentower.api.rules.auth_user.rest.controller;

import com.greentower.api.core.security.JwtTokenProvider;
import com.greentower.api.core.util.MapperUtil;
import com.greentower.api.rules.auth_user.domain.entity.AuthUser;
import com.greentower.api.rules.auth_user.rest.dto.AuthUserSessionDTO;
import com.greentower.api.rules.auth_user.rest.dto.PayloadSessionDTO;
import com.greentower.api.rules.auth_user.rest.dto.SessionLoginDTO;
import com.greentower.api.rules.auth_user.service.impl.JwtUserDetailsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    public SessionController(MapperUtil modelMapper, JwtUserDetailsService jwtUserDetailsService, JwtTokenProvider jwtTokenProvider){
        this.modelMapper = modelMapper;
        this.jwtTokenProvider = jwtTokenProvider;
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    @PostMapping(value = "/sessions", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Session Login")
    public ResponseEntity<PayloadSessionDTO> login(@RequestBody SessionLoginDTO sessionLoginDTO){
        User userDetails = jwtUserDetailsService.AuthUserAuthenticate(sessionLoginDTO);
        AuthUser authUser = jwtUserDetailsService.findByEmail(sessionLoginDTO.getEmail());
        PayloadSessionDTO payloadSessionDTO = new PayloadSessionDTO(jwtTokenProvider.generateTokenByAuthUser(userDetails), modelMapper.mapTo(authUser, AuthUserSessionDTO.class));
        return ResponseEntity.ok(payloadSessionDTO);
    }
}
