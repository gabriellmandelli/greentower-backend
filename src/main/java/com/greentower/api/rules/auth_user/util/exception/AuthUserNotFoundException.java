package com.greentower.api.rules.auth_user.util.exception;

public class AuthUserNotFoundException extends RuntimeException{
    public AuthUserNotFoundException(){
        super("User not found");
    }
}
