package com.greentower.api.rules.auth_user.service;

import com.greentower.api.core.config.PasswordEncoderConfig;
import com.greentower.api.core.security.CustomUserDetails;
import com.greentower.api.rules.auth_user.domain.entity.AuthUser;
import com.greentower.api.rules.auth_user.domain.enums.Role;
import com.greentower.api.rules.auth_user.domain.repository.AuthUserRepository;
import com.greentower.api.rules.auth_user.rest.dto.SessionLoginDTO;
import com.greentower.api.rules.auth_user.service.impl.JwtUserDetailsServiceImpl;
import com.greentower.api.rules.auth_user.util.exception.AuthUserUnauthorizedException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class JwtUserDetailsServiceTest {

    private JwtUserDetailsServiceImpl jwtUserDetailsService;

    @Mock
    private AuthUserRepository authUserRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @MockBean
    private PasswordEncoderConfig passwordEncoderConfig;

    @Before
    public void before(){
        MockitoAnnotations.initMocks(this);
        jwtUserDetailsService = new JwtUserDetailsServiceImpl(passwordEncoder, authUserRepository);
        when(passwordEncoder.matches("123456", getOptionalAuthUserPresentFromDataBase().get().getPassword())).thenReturn(true);
    }

    @After
    public void after(){
        jwtUserDetailsService = null;
    }

    @Test(expected = AuthUserUnauthorizedException.class)
    public void testLoadUserByUsernameNotExists(){
        jwtUserDetailsService.loadUserByUsername("");
    }

    @Test(expected = AuthUserUnauthorizedException.class)
    public void testLoadUserByUsernameAnPasswordNotMatches(){
        Optional<AuthUser> authUserOptional = getOptionalAuthUserPresentFromDataBase();
        AuthUser authUser = authUserOptional.get();

        when(this.authUserRepository.findByemail(authUser.getEmail())).thenReturn(authUserOptional);
        jwtUserDetailsService.AuthUserAuthenticate(new SessionLoginDTO(authUser.getEmail(), "321654"));
    }

    @Test
    public void testLoadUserByUsernameAnPasswordMatches(){
        Optional<AuthUser> authUserOptional = getOptionalAuthUserPresentFromDataBase();
        AuthUser authUser = authUserOptional.get();
        when(this.authUserRepository.findByemail(authUser.getEmail())).thenReturn(authUserOptional);
        CustomUserDetails customUserDetails = jwtUserDetailsService.AuthUserAuthenticate(new SessionLoginDTO(authUser.getEmail(), "123456"));
        Assert.assertEquals(customUserDetails.getId(), authUser.getId());
        Assert.assertEquals(customUserDetails.getPassword(), authUser.getPassword());
        Assert.assertEquals(customUserDetails.getUsername(), authUser.getEmail());
    }

    @Test(expected = AuthUserUnauthorizedException.class)
    public void testFindByEmailNotExists(){
        jwtUserDetailsService.findByEmail("");
    }

    private Optional<AuthUser> getOptionalAuthUserPresentFromDataBase(){
        AuthUser authUser = new AuthUser();
        authUser.setRole(Role.ADMIN);
        authUser.setName("Teste");
        authUser.setId(UUID.randomUUID());
        authUser.setEmail("teste@teste.com");
        authUser.setPassword("$2a$10$/Wzxz75GS865petOtlpHMOacfew3FSOj0FbzeVsGesZtwpitOC2Iu");
        authUser.setCreatedAt(LocalDateTime.now());
        authUser.setUpdatedAt(LocalDateTime.now());
        authUser.setSequential((long) 1);
        return Optional.of(authUser);
    }
}
