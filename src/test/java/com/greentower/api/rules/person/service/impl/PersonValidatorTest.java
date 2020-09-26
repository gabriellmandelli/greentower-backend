package com.greentower.api.rules.person.service.impl;

import com.greentower.api.rules.person.domain.entity.Person;
import com.greentower.api.rules.person.domain.repository.PersonRepository;
import com.greentower.api.rules.person.service.PersonService;
import com.greentower.api.rules.person.service.validation.PersonValidator;
import com.greentower.api.rules.person.util.ValidatorUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PersonValidatorTest {

    @InjectMocks
    private PersonValidator personValidator;

    @Mock
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private ValidatorUtil validatorUtil;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testPersonNameInvalid() {
        Person person = new Person();
        Assert.assertFalse(this.personValidator.isPersonNameValid(person));
    }

    @Test
    public void testPersonDateOfBirthInvalid() {
        Person person = new Person();
        Assert.assertFalse(this.personValidator.isPersonDateOfBirthValid(person));
    }

    @Test
    public void testPersonEmailInvalid() {
        Person person = new Person();
        person.setEmail("greentower@");
        Assert.assertFalse(this.personValidator.isPersonEmailValid(person));
    }

    @Test
    public void testPersonCpfInvalid() {
        Person person = new Person();
        person.setCpf("12345678912");
        Assert.assertFalse(this.personValidator.isPersonCpfValid(person));
    }

    @Test
    public void testPersonAddressInvalid() {
        Person person = new Person();
        Assert.assertFalse(this.personValidator.isPersonAddressValid(person));
    }
}