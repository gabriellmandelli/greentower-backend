package com.greentower.api.rules.person.validation;

import com.greentower.api.core.error.ObjectError;
import com.greentower.api.rules.person.domain.entity.Person;
import com.greentower.api.rules.person.domain.repository.PersonRepository;
import com.greentower.api.rules.person.service.validation.PersonValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class PersonValidatorTest {

    @InjectMocks private PersonValidator personValidator;

    @Mock private PersonRepository personRepository;

    @Before public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetErrorListFromPersonInvalid(){
        Person person = new Person();
        List<ObjectError> errorList = this.personValidator.getErrorListFromPerson(person);
        Assert.assertFalse(errorList.isEmpty());
    }

    @Test
    public void testGetErrorListFromPersonValid(){
        Person person = new Person();
        person.setDateOfBirth(Date.from(Instant.now()));
        person.setEmail("greentower@greentower.com");
        person.setCpf("00775830003");
        List<ObjectError> errorList = this.personValidator.getErrorListFromPerson(person);
        Assert.assertTrue(errorList.isEmpty());
    }

    @Test
    public void testPersonDateOfBirthInvalid() {
        Person person = new Person();
        Assert.assertFalse(this.personValidator.isPersonDateOfBirthValid(person));
    }

    @Test
    public void testPersonDateOfBirthValid() {
        Person person = new Person();
        person.setDateOfBirth(Date.from(Instant.now()));
        Assert.assertTrue(this.personValidator.isPersonDateOfBirthValid(person));
    }

    @Test
    public void testPersonEmailInvalid() {
        Person person = new Person();
        person.setEmail("greentower@");
        Assert.assertFalse(this.personValidator.isPersonEmailValid(person));
    }

    @Test
    public void testPersonEmailValid() {
        Person person = new Person();
        person.setEmail("greentower@greentower.com");
        Assert.assertTrue(this.personValidator.isPersonEmailValid(person));
    }

    @Test
    public void testPersonCpfInvalid() {
        Person person = new Person();
        person.setCpf("12345678912");
        Assert.assertFalse(this.personValidator.isPersonCpfValid(person));
    }

    @Test
    public void testPersonCpfValid() {
        Person person = new Person();
        person.setCpf("00775830003");
        Assert.assertTrue(this.personValidator.isPersonCpfValid(person));
    }
}