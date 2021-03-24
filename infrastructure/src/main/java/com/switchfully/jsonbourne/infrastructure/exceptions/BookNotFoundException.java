package com.switchfully.jsonbourne.infrastructure.exceptions;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(String message){
        super(message);
    }
}
