package com.greentower.api.rules.auth_user.rest.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.UUID;

public class AuthUserSessionDTO {

    @ApiModelProperty(value = "User id")
    private UUID id;

    @ApiModelProperty(value = "User email", example = "greentower@greentower.com", required = true)
    private String email;

    @ApiModelProperty(value = "User name", example = "Green Software", required = true)
    private String name;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AuthUserSessionDTO(UUID id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public AuthUserSessionDTO(){

    }

}
