package com.greentower.api.rules.person.util.exception;

import com.greentower.api.core.error.ObjectError;

import java.util.List;

public class PersonValidationErrorException extends RuntimeException{

    List<ObjectError> errors;

    public PersonValidationErrorException(List<ObjectError> errors){
        super("Request has invalid fields");
        this.errors = errors;
    }

    public List<ObjectError> getErrors() {
        return errors;
    }
}
