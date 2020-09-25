package com.greentower.api.rules.auth_user.domain.entity;

import com.greentower.api.core.generic.GenericClass;
import com.greentower.api.rules.auth_user.domain.enums.Role;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "auth_user")
public class AuthUser implements GenericClass, Serializable {

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role")
    private Role role;
}
