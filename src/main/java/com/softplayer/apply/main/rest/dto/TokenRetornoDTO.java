package com.softplayer.apply.main.rest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenRetornoDTO {

    @ApiModelProperty(value = "Token da sessão")
    private String token;

    @ApiModelProperty(value = "Usuário da sessão")
    private UsuarioDTO user;

    public TokenRetornoDTO(String token, UsuarioDTO user) {
        this.token = token;
        this.user = user;
    }
}
