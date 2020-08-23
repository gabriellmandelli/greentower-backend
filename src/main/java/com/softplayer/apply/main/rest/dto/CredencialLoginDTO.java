package com.softplayer.apply.main.rest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CredencialLoginDTO {

    @ApiModelProperty(value = "Email do usuário", example = "softplayer@softplayer.com")
    private String email;

    @ApiModelProperty(value = "Senha do usuário", example = "123456")
    private String password;
}
