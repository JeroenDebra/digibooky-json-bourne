package com.switchfully.jsonbourne.domain.models.book;

import com.switchfully.jsonbourne.infrastructure.exceptions.InvalidLastNameException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class Author {

    private static final Logger logger = LoggerFactory.getLogger(Author.class);

    private final String firstname;
    private final String lastname;

    public Author(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastNameValidator(lastname);
    }

    private String lastNameValidator(String lastname) {
        if (lastname == null || lastname.isBlank()) {
            logger.warn("The user tried to register an invalid last name");
            throw new InvalidLastNameException("Last name is not valid");
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(firstname, author.firstname) && Objects.equals(lastname, author.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname);
    }
}
