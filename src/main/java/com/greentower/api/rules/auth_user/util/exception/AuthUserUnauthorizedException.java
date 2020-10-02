package com.greentower.api.rules.auth_user.util.exception;

public class AuthUserUnauthorizedException extends RuntimeException{
    public AuthUserUnauthorizedException(String message){
        super(message);
    }
}
