package com.greentower.api.rules.auth_user.rest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SessionLoginDTO {

    @ApiModelProperty(value = "User email", example = "greensoftware@greensoftware.com", required = true)
    private String email;

    @ApiModelProperty(value = "User password", example = "******")
    private String password;
}
