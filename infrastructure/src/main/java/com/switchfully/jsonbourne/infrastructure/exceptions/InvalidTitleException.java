package com.switchfully.jsonbourne.infrastructure.exceptions;

public class InvalidTitleException extends RuntimeException {
    public InvalidTitleException(String message) {
        super(message);
    }
}
