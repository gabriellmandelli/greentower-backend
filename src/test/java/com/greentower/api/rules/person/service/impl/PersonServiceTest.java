package com.greentower.api.rules.person.service.impl;

import com.greentower.api.rules.person.domain.entity.Person;
import com.greentower.api.rules.person.domain.repository.PersonRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class PersonServiceTest {

    @InjectMocks
    private PersonServiceImpl personService;

    @Mock
    private PersonRepository personRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = ResponseStatusException.class)
    public void testFindByID() {
        UUID personId = UUID.randomUUID();
        personService.findById(personId);
    }

    @Test
    public void testFindAllEmpty(){
        List<Person> personList = personService.findAll();
        Assert.assertEquals(personList, Collections.emptyList());
    }
}