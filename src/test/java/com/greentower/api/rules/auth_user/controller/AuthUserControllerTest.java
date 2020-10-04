package com.greentower.api.rules.auth_user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.greentower.api.core.security.JwtTokenProvider;
import com.greentower.api.rules.auth_user.rest.dto.AuthUserDTO;
import com.greentower.api.rules.auth_user.rest.dto.AuthUserUpdateDTO;
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

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest()
public class AuthUserControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @MockBean
    private AuthenticationManager authenticationManager;

    @BeforeEach
    public void before(){
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithAnonymousUser
    public void testSaveAuthUserWithFieldsEmpty() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(createAuthUserDtoToJson(new AuthUserDTO()));

        mockMvc.perform(requestBuilder)
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    @WithAnonymousUser
    public void testUpdateAuthUserWithFieldsEmpty() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/users")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(createAuthUserUpdateDtoToJson(new AuthUserUpdateDTO()));

        mockMvc.perform(requestBuilder)
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    private static String createAuthUserDtoToJson(AuthUserDTO authUserDTO) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(authUserDTO);
    }
    private static String createAuthUserUpdateDtoToJson(AuthUserUpdateDTO authUserUpdateDTO) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(authUserUpdateDTO);
    }

}
