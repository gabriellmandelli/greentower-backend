package com.greentower.api.rules.auth_user.controller;

import com.greentower.api.core.security.JwtTokenProvider;
import com.greentower.api.rules.auth_user.domain.entity.AuthUser;
import com.greentower.api.rules.auth_user.domain.enums.Role;
import com.greentower.api.rules.auth_user.domain.repository.AuthUserRepository;
import com.greentower.api.rules.auth_user.service.impl.JwtUserDetailsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest()
public class SessionControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private JwtUserDetailsServiceImpl jwtUserDetailsService;

    @MockBean
    private AuthUserRepository mockAuthUserRepository;

    @MockBean
    private AuthenticationManager authenticationManager;

    @BeforeEach
    public void before(){
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
        when(this.mockAuthUserRepository.findByemail(getOptionalAuthUserPresentFromDataBase().get().getEmail())).thenReturn(getOptionalAuthUserPresentFromDataBase());
    }

    @Test
    @WithAnonymousUser
    public void testPostSessionWithOutSessionDTO() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/sessions").accept(MediaType.APPLICATION_JSON_VALUE);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    @WithAnonymousUser
    public void testPostSessionWithSessionDTOInvalidFields() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/sessions")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\n" +
                "  \"email\": \"greentower@greentower.com\",\n" +
                "  \"password\": \"654321\"\n" +
                "}");

        mockMvc.perform(requestBuilder)
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

    @Test
    @WithAnonymousUser
    public void testPostSessionWithSessionDTOSucess() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/sessions")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\n" +
                        "  \"email\": \"greentower@greentower.com\",\n" +
                        "  \"password\": \"123456\"\n" +
                        "}");

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();
    }

    private Optional<AuthUser> getOptionalAuthUserPresentFromDataBase(){
        AuthUser authUser = new AuthUser();
        authUser.setRole(Role.ADMIN);
        authUser.setName("Teste");
        authUser.setId(UUID.randomUUID());
        authUser.setEmail("greentower@greentower.com");
        authUser.setPassword("$2a$10$/Wzxz75GS865petOtlpHMOacfew3FSOj0FbzeVsGesZtwpitOC2Iu");
        authUser.setCreatedAt(LocalDateTime.now());
        authUser.setUpdatedAt(LocalDateTime.now());
        authUser.setSequential((long) 1);
        return Optional.of(authUser);
    }
}
