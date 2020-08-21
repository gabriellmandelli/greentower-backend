package com.softplayer.apply.main.rest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioUpdateDTO {

    private String name;

    private String email;

    private String oldPassword;

    private String password;

    private String confirmPassword;

}
