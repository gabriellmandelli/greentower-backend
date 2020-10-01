package com.greentower.api.rules.auth_user.service.impl;

import com.greentower.api.rules.auth_user.domain.entity.AuthUser;
import com.greentower.api.rules.auth_user.domain.enums.Role;
import com.greentower.api.rules.auth_user.domain.repository.AuthUserRepository;
import com.greentower.api.rules.auth_user.rest.dto.AuthUserUpdateDTO;
import com.greentower.api.rules.auth_user.service.AuthUserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class AuthUserServiceImpl implements AuthUserService {

    private final PasswordEncoder passwordEncoder;
    private final AuthUserRepository authUserRepository;

    public AuthUserServiceImpl(PasswordEncoder passwordEncoder, AuthUserRepository authUserRepository){
        this.passwordEncoder = passwordEncoder;
        this.authUserRepository = authUserRepository;
    }

    @Override
    public AuthUser save(AuthUser authUser) {
        authUser.setRole(Role.ADMIN);
        authUser.setPassword(passwordEncoder.encode(authUser.getPassword()));
        return authUserRepository.save(authUser);
    }

    @Override
    public AuthUser update(AuthUserUpdateDTO authUserUpdateDTO) {

        AuthUser authUserDb = findByEmail(authUserUpdateDTO.getEmail());

        boolean isUpdatePassword = (authUserUpdateDTO.getPassword() != null && authUserUpdateDTO.getOldPassword() != null && authUserUpdateDTO.getConfirmPassword() != null);

        if(isUpdatePassword){
            boolean isPasswordConfirms = authUserUpdateDTO.getConfirmPassword().equals(authUserUpdateDTO.getPassword());
            boolean isOldPasswordMatches = passwordEncoder.matches(authUserUpdateDTO.getOldPassword(), authUserDb.getPassword());

            if(isPasswordConfirms && isOldPasswordMatches){
                authUserDb.setPassword(passwordEncoder.encode(authUserUpdateDTO.getPassword()));
            }
        }

        authUserDb.setEmail(authUserUpdateDTO.getEmail());
        authUserDb.setName(authUserUpdateDTO.getName());
        authUserDb = authUserRepository.save(authUserDb);

        return authUserDb;
    }

    @Override
    public void delete(UUID authUserId) {
        authUserRepository.deleteById(authUserId);
    }

    @Override
    public void deleteAll() {
        authUserRepository.deleteAll();
    }

    @Override
    public List<AuthUser> findAll() {
        return authUserRepository.findAll();
    }

    @Override
    public AuthUser findByEmail(String authUserEmail) {
        return authUserRepository.findByemail(authUserEmail).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found in database"));
    }
}
