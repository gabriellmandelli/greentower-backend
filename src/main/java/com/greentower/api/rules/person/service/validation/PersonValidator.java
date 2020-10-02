package com.greentower.api.rules.person.service.validation;

import com.greentower.api.core.error.ObjectError;
import com.greentower.api.rules.person.domain.entity.Person;
import com.greentower.api.rules.person.domain.repository.PersonRepository;
import com.greentower.api.rules.person.util.ValidatorUtil;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PersonValidator {

    private final PersonRepository personRepository;

    public PersonValidator(PersonRepository personRepository){
        this.personRepository = personRepository;
    }
    
    public List<ObjectError> getErrorListFromPerson(Person person){
        
        List<ObjectError> errorList = new ArrayList<>();
        
        if(!isPersonDateOfBirthValid(person)){
            ObjectError error = new ObjectError("Date of birth is not null", "Date of birth", "");
            errorList.add(error);
        }

        if (!isPersonCpfValid(person)){
            ObjectError error = new ObjectError("Cpf invalid", "Cpf", "");
            errorList.add(error);
        }

        if(!isPersonCpfExistsInDataBase(person)){
            ObjectError error = new ObjectError("Cpf exist on database", "Cpf", "");
            errorList.add(error);
        }

        if (!isPersonEmailValid(person)){
            ObjectError error = new ObjectError("Email invalid", "Email", "");
            errorList.add(error);
        }

        return errorList;
    } 
    
    public Boolean isPersonDateOfBirthValid(Person person){
        return !(person.getDateOfBirth() == null || person.getDateOfBirth().after(Date.from(Instant.now())));
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
