package com.switchfully.jsonbourne.domain.models;


public class PersonalInformation {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String INSS;

    public PersonalInformation(String firstName, String lastName, String email, String INSS) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.INSS = INSS;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getINSS() {
        return INSS;
    }
}
