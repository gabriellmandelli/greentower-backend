package com.greentower.api.rules.person.service.impl;

import com.greentower.api.rules.person.domain.repository.PersonRepository;
import com.greentower.api.rules.person.service.PersonService;
import com.greentower.api.rules.person.domain.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person update(UUID id, Person person) {
        person.setId(id);
        return personRepository.save(person);
    }

    @Override
    public void delete(UUID personId) {
        personRepository.deleteById(personId);
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAllByOrderByNome();
    }

    @Override
    public Person findById(UUID personId) {
        return personRepository.findById(personId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Person id: " + personId.toString() + " not found."));
    }
}
