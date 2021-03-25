package com.switchfully.jsonbourne.domain.models.member;

import com.switchfully.jsonbourne.infrastructure.utils.EmailUtils;
import java.util.UUID;

public class Admin {

    private final UUID id;
    private final String firstname;
    private final String lastname;
    private final String email;

    public Admin(String firstname, String lastname, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = validEmail(email);
        id = UUID.randomUUID();
    }

    public Admin(UUID id, String firstname, String lastname, String email) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = validEmail(email);
    }

    private String validEmail(String email){
        if (!EmailUtils.isValidEmail(email)){
            throw new IllegalArgumentException("email is not valid");
        }
        return email;
    }

    public UUID getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

}
