package com.jaqueMate.domain.exceptions;

public class InvalidCredentialException extends RuntimeException{
    public InvalidCredentialException(String message) {
        super(message);
    }
}
