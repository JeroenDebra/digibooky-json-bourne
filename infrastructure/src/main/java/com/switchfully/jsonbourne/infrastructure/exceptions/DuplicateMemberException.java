package com.switchfully.jsonbourne.infrastructure.exceptions;

public class DuplicateMemberException extends RuntimeException {

    public DuplicateMemberException (String message) {
        super(message);
    }
}
