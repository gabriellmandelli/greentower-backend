package com.greentower.api.rules.person.rest.controller;

import com.greentower.api.core.util.MapperUtil;
import com.greentower.api.rules.person.domain.entity.Person;
import com.greentower.api.rules.person.rest.dto.PersonDTOv2;
import com.greentower.api.rules.person.service.PersonService;
import com.greentower.api.rules.person.service.validation.PersonValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/person/v2")
@Api(value = "Person Api Rest v2")
@CrossOrigin(value = "*")
public class PersonControllerV2 {

    private final MapperUtil modelMapper;
    private final PersonService personService;
    private final PersonValidator personValidator;

    @Autowired
    public PersonControllerV2(MapperUtil modelMapper, PersonService personService, PersonValidator personValidator){
        this.modelMapper = modelMapper;
        this.personService = personService;
        this.personValidator = personValidator;
    }

    @PostMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create person")
    public ResponseEntity<PersonDTOv2> save(@RequestBody PersonDTOv2 personDTO){
        Person person = modelMapper.mapTo(personDTO, Person.class);

        personValidator.isPersonNameValid(person);
        personValidator.isPersonEmailValid(person);
        personValidator.isPersonDateOfBirthValid(person);
        personValidator.isPersonCpfValid(person);
        personValidator.isPersonCpfExistsInDataBase(person);
        personValidator.isPersonAddressValid(person);

        person = personService.save(person);
        return ResponseEntity.ok(modelMapper.mapTo(person, PersonDTOv2.class));
    }

    @PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Update person")
    public ResponseEntity<PersonDTOv2> update(@RequestBody PersonDTOv2 personDTOv2, @PathVariable("id")UUID id){
        Person person = modelMapper.mapTo(personDTOv2, Person.class);
        person.setId(id);

        personValidator.isPersonNameValid(person);
        personValidator.isPersonEmailValid(person);
        personValidator.isPersonDateOfBirthValid(person);
        personValidator.isPersonCpfValid(person);
        personValidator.isPersonCpfExistsInDataBase(person);
        personValidator.isPersonAddressValid(person);

        person = personService.update(id, person);
        return ResponseEntity.ok(modelMapper.mapTo(person, PersonDTOv2.class));
    }

    @GetMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Find all people")
    public ResponseEntity<List<PersonDTOv2>> findAll(){
        List<Person> personList = personService.findAll();
        if (personList.isEmpty()){
            return ResponseEntity.ok(Collections.emptyList());
        }else{
            return ResponseEntity.ok(modelMapper.mapTo(personList, PersonDTOv2.class));
        }
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Find person by id")
    public ResponseEntity<PersonDTOv2> findById(@PathVariable("id")UUID id){
        return ResponseEntity.ok(modelMapper.mapTo(personService.findById(id), PersonDTOv2.class));
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Remove person by id")
    public void deleteById(@PathVariable("id")UUID id){
        personService.delete(id);
    }
}
