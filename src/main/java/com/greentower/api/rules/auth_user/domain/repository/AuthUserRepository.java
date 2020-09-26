package com.greentower.api.rules.auth_user.domain.repository;

import com.greentower.api.core.generic.GenericRepository;
import com.greentower.api.rules.auth_user.domain.entity.AuthUser;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthUserRepository extends GenericRepository<AuthUser> {
    Optional<AuthUser> findByemail(String email);
}
