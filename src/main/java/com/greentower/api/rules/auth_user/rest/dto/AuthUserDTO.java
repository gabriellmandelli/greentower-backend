package com.greentower.api.rules.auth_user.rest.dto;

import com.greentower.api.rules.auth_user.domain.enums.Role;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AuthUserDTO {

    @ApiModelProperty(value = "User id")
    private UUID id;

    @ApiModelProperty(value = "User email", example = "greensoftware@greensoftware.com", required = true)
    private String email;

    @ApiModelProperty(value = "User name", example = "Green Software", required = true)
    private String name;

    @ApiModelProperty(value = "User password", example = "******")
    private String password;

    @ApiModelProperty(value = "User role", example = "ADMIN", required = true)
    private Role role;
}
