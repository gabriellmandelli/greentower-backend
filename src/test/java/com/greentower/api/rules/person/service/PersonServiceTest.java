package com.greentower.api.rules.person.service;

import com.greentower.api.rules.person.domain.entity.Person;
import com.greentower.api.rules.person.domain.repository.PersonRepository;
import com.greentower.api.rules.person.service.impl.PersonServiceImpl;
import com.greentower.api.rules.person.util.exception.PersonNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest {

    @InjectMocks private PersonServiceImpl personServiceImpl;

    @Mock private PersonRepository personRepository;

    @Test
    public void testFindAllIsEmpty(){
        List<Person> personList = new ArrayList<>();
        assertEquals(personList, personServiceImpl.findAll());
    }

    @Test(expected = PersonNotFoundException.class)
    public void testFindByIdNotFound(){
        UUID randomId = UUID.randomUUID();
        personServiceImpl.findById(randomId);
    }

}