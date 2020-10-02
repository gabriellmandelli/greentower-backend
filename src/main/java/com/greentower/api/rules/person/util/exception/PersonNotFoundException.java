package com.greentower.api.rules.person.util.exception;

public class PersonNotFoundException extends RuntimeException{

    public PersonNotFoundException(){
        super("Person not found");
    }

}
