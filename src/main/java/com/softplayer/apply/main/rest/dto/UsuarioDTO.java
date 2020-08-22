package com.softplayer.apply.main.rest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UsuarioDTO {

    @ApiModelProperty(value = "Id do usuario")
    private UUID id;

    @ApiModelProperty(value = "Email do usuario", example = "softplayer@softplayer.com", required = true)
    private String email;

    @ApiModelProperty(value = "Nome do usuario", example = "Softplayer", required = true)
    private String name;

    @ApiModelProperty(value = "Senha do usuario", example = "******", required = true)
    private String password;
}
