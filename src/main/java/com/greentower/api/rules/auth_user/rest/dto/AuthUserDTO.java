package com.greentower.api.rules.auth_user.rest.dto;

import com.greentower.api.rules.auth_user.domain.enums.Role;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class AuthUserDTO {

    @ApiModelProperty(value = "User id")
    private UUID id;

    @ApiModelProperty(value = "User email", example = "greentower@greentower.com", required = true)
    @NotBlank
    private String email;

    @ApiModelProperty(value = "User name", example = "Green Software", required = true)
    @NotBlank
    private String name;

    @ApiModelProperty(value = "User password", example = "******")
    @NotBlank
    private String password;

    @ApiModelProperty(value = "User role", example = "ADMIN", required = true)
    private Role role;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
