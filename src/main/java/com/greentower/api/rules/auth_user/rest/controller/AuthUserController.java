package com.greentower.api.rules.auth_user.rest.controller;

import com.greentower.api.core.util.MapperUtil;
import com.greentower.api.rules.auth_user.domain.entity.AuthUser;
import com.greentower.api.rules.auth_user.rest.dto.AuthUserDTO;
import com.greentower.api.rules.auth_user.rest.dto.AuthUserUpdateDTO;
import com.greentower.api.rules.auth_user.service.AuthUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Api(value = "User rest api")
@CrossOrigin(value = "*")
public class AuthUserController {

    private final AuthUserService authUserService;
    private final MapperUtil modelMapper;

    public AuthUserController(AuthUserService authUserService, MapperUtil modelMapper){
        this.authUserService = authUserService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create User")
    public ResponseEntity<AuthUserDTO> save(@RequestBody AuthUserDTO authUserDTO){
        AuthUser authUserDB = modelMapper.mapTo(authUserDTO, AuthUser.class);
        return ResponseEntity.ok(modelMapper.mapTo(authUserService.save(authUserDB), AuthUserDTO.class));
    }

    @PutMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Update User")
    public ResponseEntity<AuthUserDTO> update(@RequestBody AuthUserUpdateDTO authUserUpdateDTO){
        return ResponseEntity.ok(modelMapper.mapTo(authUserService.update(authUserUpdateDTO), AuthUserDTO.class));
    }
}
