package com.greentower.api.rules.person.service.impl;

import com.greentower.api.rules.person.domain.entity.Person;
import com.greentower.api.rules.person.domain.enums.Gender;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.UUID;

@SpringBootTest
public class PersonEntityTest {

    @Test
    public void testPerson() {
        Person person = new Person();
        Date dataNascimento = new Date();
        UUID personId = UUID.randomUUID();

        person.setId(personId);
        person.setName("GreenTower");
        person.setEmail("greentower@greentower.com");
        person.setGender(Gender.MALE);
        person.setDateOfBirth(dataNascimento);
        person.setNaturalness("Veneziano");
        person.setNationality("Brasileiro");
        person.setCpf("12345678912");

        Assert.assertEquals(person.getId(), personId);
        Assert.assertEquals(person.getName(), "GreenTower");
        Assert.assertEquals(person.getEmail(), "greentower@greentower.com");
        Assert.assertEquals(person.getGender(), Gender.MALE);
        Assert.assertEquals(person.getDateOfBirth(), dataNascimento);
        Assert.assertEquals(person.getNaturalness(), "Veneziano");
        Assert.assertEquals(person.getNationality(), "Brasileiro");
        Assert.assertEquals(person.getCpf(), "12345678912");
    }
}