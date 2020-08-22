package com.softplayer.apply.main.rest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenRetornoDTO {

    private String token;

    private UsuarioDTO user;

    public TokenRetornoDTO(String token, UsuarioDTO user) {
        this.token = token;
        this.user = user;
    }
}
