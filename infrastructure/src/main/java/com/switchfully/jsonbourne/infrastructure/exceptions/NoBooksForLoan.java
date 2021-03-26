package com.switchfully.jsonbourne.infrastructure.exceptions;

public class NoBooksForLoan extends RuntimeException {
    public NoBooksForLoan(String message) {
        super(message);
    }
}
