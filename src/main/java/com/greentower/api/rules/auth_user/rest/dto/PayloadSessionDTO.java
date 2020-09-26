package com.greentower.api.rules.auth_user.rest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PayloadSessionDTO {

    @ApiModelProperty(value = "Session Token")
    private String token;

    @ApiModelProperty(value = "Session User")
    private AuthUserSessionDTO user;

    public PayloadSessionDTO(String token, AuthUserSessionDTO user) {
        this.token = token;
        this.user = user;
    }
}
