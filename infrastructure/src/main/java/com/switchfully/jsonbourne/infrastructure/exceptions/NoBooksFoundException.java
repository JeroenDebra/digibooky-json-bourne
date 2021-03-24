package com.switchfully.jsonbourne.infrastructure.exceptions;

public class NoBooksFoundException extends RuntimeException {

    public NoBooksFoundException(String message) {
        super(message);
    }
}
