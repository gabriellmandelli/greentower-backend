package com.greentower.api.rules.auth_user.rest.dto;

import io.swagger.annotations.ApiModelProperty;

public class PayloadSessionDTO {

    @ApiModelProperty(value = "Session Token")
    private String token;

    @ApiModelProperty(value = "Session User")
    private AuthUserSessionDTO user;

    public PayloadSessionDTO(String token, AuthUserSessionDTO user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public AuthUserSessionDTO getUser() {
        return user;
    }

    public void setUser(AuthUserSessionDTO user) {
        this.user = user;
    }
}
