package com.switchfully.jsonbourne.domain.models.book;

public class Author {

    private final String firstname;
    private final String lastname;

    public Author(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastNameValidator(lastname);
    }

    private String lastNameValidator(String lastname) {
        if (lastname == null || lastname.isBlank()) {
            throw new IllegalArgumentException("lastname is not valid");
        }
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFullname() {
        return firstname + " " + lastname;
    }
}
