package com.switchfully.jsonbourne.domain.models.member;


import java.util.regex.Pattern;

public class PersonalInformation {

    private static final String VALID_EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";

    private final String firstName;
    private final String lastName;
    private final String email;
    private final String INSS;

    public PersonalInformation(String firstName, String lastName, String email, String INSS) {
        this.firstName = firstName;
        this.lastName = lastNameValidator(lastName);
        this.email = emailValidator(email);
        this.INSS = INSSValidator(INSS);
    }

    private String lastNameValidator(String lastName) {
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("lastname not valid");
        }
        return lastName;
    }

    private String emailValidator(String email) {
        if (lastName == null || !Pattern.compile(VALID_EMAIL_REGEX).matcher(email).matches()) {
            throw new IllegalArgumentException("email not valid");
        }
        return email;
    }

    private String INSSValidator(String INSS){
        if (INSS == null || INSS.isBlank()) {
            throw new IllegalArgumentException("INSS not valid");
        }
        return INSS;
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
