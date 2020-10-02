package com.greentower.api.rules.auth_user.rest.controller;

import com.greentower.api.core.security.CustomUserDetails;
import com.greentower.api.core.security.JwtTokenProvider;
import com.greentower.api.core.util.MapperUtil;
import com.greentower.api.rules.auth_user.domain.entity.AuthUser;
import com.greentower.api.rules.auth_user.rest.dto.AuthUserSessionDTO;
import com.greentower.api.rules.auth_user.rest.dto.PayloadSessionDTO;
import com.greentower.api.rules.auth_user.rest.dto.SessionLoginDTO;
import com.greentower.api.rules.auth_user.service.impl.JwtUserDetailsServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("")
@Api(value = "Api Rest Authentication")
@CrossOrigin(value = "*")
public class SessionController {

    private final MapperUtil modelMapper;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtUserDetailsServiceImpl jwtUserDetailsServiceImpl;

    public SessionController(MapperUtil modelMapper, JwtTokenProvider jwtTokenProvider, JwtUserDetailsServiceImpl jwtUserDetailsServiceImpl){
        this.modelMapper = modelMapper;
        this.jwtTokenProvider = jwtTokenProvider;
        this.jwtUserDetailsServiceImpl = jwtUserDetailsServiceImpl;
    }

    @PostMapping(value = "/sessions", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Session Login")
    public ResponseEntity<PayloadSessionDTO> login(@RequestBody  @Valid SessionLoginDTO sessionLoginDTO){
        CustomUserDetails customUserDetails = jwtUserDetailsServiceImpl.AuthUserAuthenticate(sessionLoginDTO);
        AuthUser authUser = jwtUserDetailsServiceImpl.findByEmail(sessionLoginDTO.getEmail());
        String tokenReturn = jwtTokenProvider.generateTokenByAuthUser(customUserDetails);
        AuthUserSessionDTO authUserSessionDTO = modelMapper.mapTo(authUser, AuthUserSessionDTO.class);
        return ResponseEntity.ok(new PayloadSessionDTO(tokenReturn, authUserSessionDTO));
    }
}
