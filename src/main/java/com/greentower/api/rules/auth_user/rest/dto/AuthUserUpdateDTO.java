package com.greentower.api.rules.auth_user.rest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthUserUpdateDTO {

    @ApiModelProperty(value = "User name", example = "Green Software", required = true)
    private String name;

    @ApiModelProperty(value = "User email", example = "greensoftware@greensoftware.com", required = true)
    private String email;

    @ApiModelProperty(value = "User password", example = "******")
    private String oldPassword;

    @ApiModelProperty(value = "New user password", example = "******", required = true)
    private String password;

    @ApiModelProperty(value = "New user password confirmation", example = "******", required = true)
    private String confirmPassword;

}
