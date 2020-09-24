package com.greentower.api.rules.auth_user.rest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenRetornoDTO {

    @ApiModelProperty(value = "Token da sessão")
    private String token;

    @ApiModelProperty(value = "Usuário da sessão")
    private UsuarioSessionDTO user;

    public TokenRetornoDTO(String token, UsuarioSessionDTO user) {
        this.token = token;
        this.user = user;
    }
}
