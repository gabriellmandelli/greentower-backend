package com.greentower.api.rules.auth_user.service.impl;

import com.greentower.api.rules.auth_user.domain.entity.AuthUser;
import com.greentower.api.rules.auth_user.domain.repository.AuthUserRepository;
import com.greentower.api.rules.auth_user.rest.dto.AuthUserUpdateDTO;
import com.greentower.api.rules.auth_user.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class AuthUserServiceImpl implements AuthUserService {

    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthUserServiceImpl(AuthUserRepository authUserRepository, PasswordEncoder passwordEncoder){
        this.authUserRepository = authUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthUser save(AuthUser authUser) {
        authUser.setPassword(passwordEncoder.encode(authUser.getPassword()));
        return authUserRepository.save(authUser);
    }

    @Override
    public AuthUser update(AuthUserUpdateDTO authUserUpdateDTO) {

        AuthUser authUserDb = findByEmail(authUserUpdateDTO.getEmail());

        boolean lbAtualizaPassword = (authUserUpdateDTO.getPassword() != null && authUserUpdateDTO.getOldPassword() != null && authUserUpdateDTO.getConfirmPassword() != null);

        if (lbAtualizaPassword){
            lbAtualizaPassword = authUserUpdateDTO.getConfirmPassword().equals(authUserUpdateDTO.getPassword());
            if (lbAtualizaPassword && passwordEncoder.matches(authUserUpdateDTO.getOldPassword(), authUserDb.getPassword())){
                authUserDb.setPassword(passwordEncoder.encode(authUserUpdateDTO.getPassword()));
            }
        }

        authUserDb.setEmail(authUserUpdateDTO.getEmail());
        authUserDb.setName(authUserUpdateDTO.getName());
        authUserDb = authUserRepository.save(authUserDb);
        authUserDb.setPassword("");

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
