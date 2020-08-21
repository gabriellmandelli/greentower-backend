package com.softplayer.apply.main.rest.dto;

import com.softplayer.apply.main.domain.entity.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenRetornoDTO {

    private String token;

    private Usuario user;

    public TokenRetornoDTO(String token, Usuario user) {
        this.token = token;
        this.user = user;
    }
}
