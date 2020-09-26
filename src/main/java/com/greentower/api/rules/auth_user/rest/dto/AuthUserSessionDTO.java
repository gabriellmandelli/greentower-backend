package com.greentower.api.rules.auth_user.rest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AuthUserSessionDTO {

    @ApiModelProperty(value = "User id")
    private UUID id;

    @ApiModelProperty(value = "User email", example = "greensoftware@greensoftware.com", required = true)
    private String email;

    @ApiModelProperty(value = "User name", example = "Green Software", required = true)
    private String name;
}
