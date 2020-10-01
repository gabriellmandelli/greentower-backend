package com.greentower.api.rules.auth_user.service.impl;

import com.greentower.api.core.security.CustomUserDetails;
import com.greentower.api.rules.auth_user.domain.entity.AuthUser;
import com.greentower.api.rules.auth_user.domain.repository.AuthUserRepository;
import com.greentower.api.rules.auth_user.rest.dto.SessionLoginDTO;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final AuthUserRepository authUserRepository;

    public JwtUserDetailsServiceImpl(PasswordEncoder passwordEncoder, AuthUserRepository authUserRepository) {
        this.passwordEncoder = passwordEncoder;
        this.authUserRepository = authUserRepository;
    }

    public AuthUser findByEmail(String email){
        return authUserRepository.findByemail(email).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email or password not matches")
        );
    }

    @Override
    public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AuthUser authUserDb = this.findByEmail(email);
        return new CustomUserDetails(
                authUserDb.getId(),
                authUserDb.getEmail(),
                authUserDb.getPassword(),
                AuthorityUtils.createAuthorityList("ROLE_" + authUserDb.getRole().toString())
        );
    }

    public CustomUserDetails AuthUserAuthenticate(SessionLoginDTO sessionLoginDTO) {
        CustomUserDetails userDetails = loadUserByUsername(sessionLoginDTO.getEmail());
        if (passwordEncoder.matches(sessionLoginDTO.getPassword(), userDetails.getPassword())){
            return userDetails;
        }else{
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email or password not matches");
        }
    }
}
