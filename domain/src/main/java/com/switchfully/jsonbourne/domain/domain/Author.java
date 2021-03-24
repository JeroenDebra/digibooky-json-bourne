package com.switchfully.jsonbourne.domain.domain;

public class Author {

    private final String firstname;
    private final String lastname;

    public Author(String firstname, String lastname){
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFullname(){
        return firstname + " " + lastname;
    }
}