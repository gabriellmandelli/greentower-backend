package com.greentower.api.rules.person.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.greentower.api.rules.person.domain.repository.PersonRepository;
import com.greentower.api.rules.person.rest.controller.PersonController;
import com.greentower.api.rules.person.rest.dto.PersonDTO;
import com.greentower.api.rules.person.service.impl.PersonServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Date;
import java.time.Instant;
import java.util.UUID;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest()
public class PersonControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private PersonController personController;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private PersonServiceImpl personService;

    @MockBean
    private PersonRepository personRepository;

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
    public void testSavePersonIsForbidden() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/person/v1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(createPersonDtoToJson(new PersonDTO()));

        mockMvc.perform(requestBuilder)
                .andExpect(status().isForbidden())
                .andReturn();
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void testSavePersonDtoFieldsNullIsBadRequest() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/person/v1")
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJncmVlbnRvd2VyQGdyZWVudG93ZXIuY29tIiwic2NvcGVzIjpbeyJhdXRob3JpdHkiOiJST0xFX0FETUlOIn1dLCJpYXQiOjE2MDE4MzQwNzYsImV4cCI6MTYwMTg1MjA3Nn0.3vKISPCu7nc_EwteUvGhUja4hE4CJsXy4emRkj5DNZI")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(createPersonDtoToJson(new PersonDTO()));

        mockMvc.perform(requestBuilder)
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void testSavePersonDtoInvalidIsBadRequest() throws Exception {

        PersonDTO personDTO = new PersonDTO();
        personDTO.setCpf("123456468");
        personDTO.setDateOfBirth(Date.from(Instant.now()));
        personDTO.setEmail("greentower@");
        personDTO.setName("Teste");

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/person/v1")
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJncmVlbnRvd2VyQGdyZWVudG93ZXIuY29tIiwic2NvcGVzIjpbeyJhdXRob3JpdHkiOiJST0xFX0FETUlOIn1dLCJpYXQiOjE2MDE4MzQwNzYsImV4cCI6MTYwMTg1MjA3Nn0.3vKISPCu7nc_EwteUvGhUja4hE4CJsXy4emRkj5DNZI")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(createPersonDtoToJson(personDTO));

        mockMvc.perform(requestBuilder)
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void testUpdatePersonDtoFieldsNullIsBadRequest() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/person/v1/{id}", "7ae4d046-0c8f-41e2-b383-fed371082a4d")
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJncmVlbnRvd2VyQGdyZWVudG93ZXIuY29tIiwic2NvcGVzIjpbeyJhdXRob3JpdHkiOiJST0xFX0FETUlOIn1dLCJpYXQiOjE2MDE4MzQwNzYsImV4cCI6MTYwMTg1MjA3Nn0.3vKISPCu7nc_EwteUvGhUja4hE4CJsXy4emRkj5DNZI")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(createPersonDtoToJson(new PersonDTO()));

        mockMvc.perform(requestBuilder)
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void testUpdatePersonDtoInvalidIsBadRequest() throws Exception {

        PersonDTO personDTO = new PersonDTO();
        personDTO.setCpf("123456468");
        personDTO.setDateOfBirth(Date.from(Instant.now()));
        personDTO.setEmail("greentower@");
        personDTO.setName("Teste");
        personDTO.setId(UUID.fromString("7ae4d046-0c8f-41e2-b383-fed371082a4d"));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/person/v1/{id}", "7ae4d046-0c8f-41e2-b383-fed371082a4d")
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJncmVlbnRvd2VyQGdyZWVudG93ZXIuY29tIiwic2NvcGVzIjpbeyJhdXRob3JpdHkiOiJST0xFX0FETUlOIn1dLCJpYXQiOjE2MDE4MzQwNzYsImV4cCI6MTYwMTg1MjA3Nn0.3vKISPCu7nc_EwteUvGhUja4hE4CJsXy4emRkj5DNZI")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(createPersonDtoToJson(personDTO));

        mockMvc.perform(requestBuilder)
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    @WithAnonymousUser
    public void testGetPersonByIdIsForbidden() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/person/v1/{id}", "7ae4d046-0c8f-41e2-b383-fed371082a4d")
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJncmVlbnRvd2VyQGdyZWVudG93ZXIuY29tIiwic2NvcGVzIjpbeyJhdXRob3JpdHkiOiJST0xFX0FETUlOIn1dLCJpYXQiOjE2MDE4MzQwNzYsImV4cCI6MTYwMTg1MjA3Nn0.3vKISPCu7nc_EwteUvGhUja4hE4CJsXy4emRkj5DNZI")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(createPersonDtoToJson(new PersonDTO()));

        mockMvc.perform(requestBuilder)
                .andExpect(status().isForbidden())
                .andReturn();
    }

    @Test
    @WithAnonymousUser
    public void testGetAllPersonIsForbidden() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/person/v1")
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJncmVlbnRvd2VyQGdyZWVudG93ZXIuY29tIiwic2NvcGVzIjpbeyJhdXRob3JpdHkiOiJST0xFX0FETUlOIn1dLCJpYXQiOjE2MDE4MzQwNzYsImV4cCI6MTYwMTg1MjA3Nn0.3vKISPCu7nc_EwteUvGhUja4hE4CJsXy4emRkj5DNZI")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(createPersonDtoToJson(new PersonDTO()));

        mockMvc.perform(requestBuilder)
                .andExpect(status().isForbidden())
                .andReturn();
    }

    private static String createPersonDtoToJson(PersonDTO personDTO) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(personDTO);
    }
}
