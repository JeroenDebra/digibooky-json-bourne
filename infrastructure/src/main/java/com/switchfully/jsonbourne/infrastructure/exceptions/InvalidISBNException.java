package com.switchfully.jsonbourne.infrastructure.exceptions;

public class InvalidISBNException extends RuntimeException {
    public InvalidISBNException(String message) {
        super(message);
    }
}
