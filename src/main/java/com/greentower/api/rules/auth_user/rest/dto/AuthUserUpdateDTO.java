package com.greentower.api.rules.auth_user.rest.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

public class AuthUserUpdateDTO {

    @ApiModelProperty(value = "User name", example = "Green Software", required = true)
    @NotBlank
    private String name;

    @ApiModelProperty(value = "User email", example = "greentower@greentower.com", required = true)
    @NotBlank
    private String email;

    @ApiModelProperty(value = "User password", example = "******")
    private String oldPassword;

    @ApiModelProperty(value = "New user password", example = "******")
    private String password;

    @ApiModelProperty(value = "New user password confirmation", example = "******")
    private String confirmPassword;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
