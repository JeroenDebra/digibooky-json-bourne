package com.switchfully.jsonbourne.infrastructure.exceptions;

public class InvalidEmailException extends RuntimeException {

    public InvalidEmailException(String message) { super(message); }
}
