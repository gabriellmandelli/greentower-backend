package com.greentower.api.rules.person.service.validation;

import com.greentower.api.rules.person.domain.entity.Person;
import com.greentower.api.rules.person.domain.repository.PersonRepository;
import com.greentower.api.rules.person.util.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.Instant;
import java.util.Optional;

@Component
public class PersonValidator {

    private final PersonRepository personRepository;

    @Autowired
    public PersonValidator(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public Boolean isPersonNameValid(Person person){
        return !(person.getName() == null || person.getName().isEmpty());
    }

    public Boolean isPersonDateOfBirthValid(Person person){
        return (!(person.getDateOfBirth() == null || person.getDateOfBirth().after(Date.from(Instant.now()))));
    }

    public Boolean isPersonCpfValid(Person person){
        return (ValidatorUtil.isCpfValid(person.getCpf()));
    }

    public Boolean isPersonCpfExistsInDataBase(Person person){
        Optional<Person> personDb = personRepository.findBycpf(person.getCpf());
        return personDb.map(value -> value.getId().equals(person.getId())).orElse(true);
    }

    public Boolean isPersonEmailValid(Person person){
        return (ValidatorUtil.isValidEmailAddress(person.getEmail()) && !person.getEmail().isEmpty());
    }
}
