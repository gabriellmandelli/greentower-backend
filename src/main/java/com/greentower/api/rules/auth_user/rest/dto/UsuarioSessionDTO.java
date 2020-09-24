package com.greentower.api.rules.auth_user.rest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UsuarioSessionDTO {

    @ApiModelProperty(value = "Id do usuário")
    private UUID id;

    @ApiModelProperty(value = "Email do usuário", example = "softplayer@softplayer.com", required = true)
    private String email;

    @ApiModelProperty(value = "Nome do usuário", example = "Softplayer", required = true)
    private String name;
}
