package com.greentower.api.rules.auth_user.service;

import com.greentower.api.rules.auth_user.domain.entity.AuthUser;
import com.greentower.api.rules.auth_user.rest.dto.AuthUserUpdateDTO;

import java.util.List;
import java.util.UUID;

public interface AuthUserService {
    AuthUser save(AuthUser authUser);

    AuthUser update(AuthUserUpdateDTO authUserUpdateDTO);

    void delete(UUID authUserId);

    void deleteAll();

    List<AuthUser> findAll();

    AuthUser findByEmail(String email);
}
