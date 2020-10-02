package com.greentower.api.rules.auth_user.util.exception;

import com.greentower.api.core.error.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthUserControllerAdvice {

    @ExceptionHandler(AuthUserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handlerAuthUserNotFoundException(AuthUserNotFoundException authUserNotFoundException ){
        ErrorResponse errorResponse = getAuthUserNotFoundExceptionResponse(authUserNotFoundException, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AuthUserUnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<Object> handlerAuthUserUnauthorizedException(AuthUserUnauthorizedException authUserUnauthorizedException ){
        ErrorResponse errorResponse = getAuthUserUnauthorizedExceptionResponse(authUserUnauthorizedException, HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    private ErrorResponse getAuthUserNotFoundExceptionResponse(AuthUserNotFoundException authUserNotFoundException,
                                                               HttpStatus httpStatus){
        return new ErrorResponse(
                authUserNotFoundException.getMessage(),
                httpStatus.value(),
                httpStatus.getReasonPhrase(),
                authUserNotFoundException.getClass().getSimpleName(),
                null
        );
    }

    private ErrorResponse getAuthUserUnauthorizedExceptionResponse(AuthUserUnauthorizedException authUserNotFoundException,
                                                                   HttpStatus httpStatus){
        return new ErrorResponse(
                authUserNotFoundException.getMessage(),
                httpStatus.value(),
                httpStatus.getReasonPhrase(),
                authUserNotFoundException.getClass().getSimpleName(),
                null
        );
    }
}
