package com.greentower.api.rules.person.service;

import com.greentower.api.rules.person.domain.entity.Person;

import java.util.List;
import java.util.UUID;

public interface PersonService {

    Person save(Person person);

    Person update(UUID id, Person person);

    void delete(UUID personId);

    List<Person> findAll();

    Person findById(UUID id);
}
