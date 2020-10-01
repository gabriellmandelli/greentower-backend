package com.greentower.api.rules.auth_user.rest.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

public class SessionLoginDTO {

    @ApiModelProperty(value = "User email", example = "greentower@greentower.com", required = true)
    @NotBlank
    private String email;

    @ApiModelProperty(value = "User password", example = "******")
    @NotBlank
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
