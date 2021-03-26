package com.switchfully.jsonbourne.infrastructure.exceptions;

public class MemberNotFoundException extends RuntimeException{

    public MemberNotFoundException(String message){
        super(message);
    }
}
