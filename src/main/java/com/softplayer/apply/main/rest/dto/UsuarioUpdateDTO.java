package com.softplayer.apply.main.rest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioUpdateDTO {

    @ApiModelProperty(value = "Nome do usuário", example = "Softplayer", required = true)
    private String name;

    @ApiModelProperty(value = "Email do usuário", example = "softplayer@softplayer.com", required = true)
    private String email;

    @ApiModelProperty(value = "Senha antiga do usuário", example = "******")
    private String oldPassword;

    @ApiModelProperty(value = "Nova senha do usuário", example = "******", required = true)
    private String password;

    @ApiModelProperty(value = "Nova senha do usuário", example = "******", required = true)
    private String confirmPassword;

}
