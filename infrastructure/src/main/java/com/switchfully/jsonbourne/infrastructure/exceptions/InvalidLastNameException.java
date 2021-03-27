package com.switchfully.jsonbourne.infrastructure.exceptions;

public class InvalidLastNameException extends RuntimeException {

    public InvalidLastNameException (String message) {
        super(message);
    }
}
