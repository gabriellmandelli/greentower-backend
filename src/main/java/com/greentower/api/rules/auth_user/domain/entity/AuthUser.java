package com.greentower.api.rules.auth_user.domain.entity;

import com.greentower.api.core.generic.AbstractAuditingEntity;
import com.greentower.api.rules.auth_user.domain.enums.Role;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "auth_user")
public class AuthUser extends AbstractAuditingEntity implements Serializable {

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    private Role role;

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

    public AuthUser(UUID id, Long sequential, LocalDateTime createdAt, LocalDateTime updatedAt, String email, String name, String password, Role role) {
        super(id, sequential, createdAt, updatedAt);
        this.email = email;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public AuthUser(){
        super();
    }

}
