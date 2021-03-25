package com.switchfully.jsonbourne.domain.models.book;

public class Author {

    private String firstname;
    private String lastname;

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

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFullname() {
        return firstname + " " + lastname;
    }
}
