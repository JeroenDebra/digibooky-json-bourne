package com.switchfully.jsonbourne.domain.models.member;

import com.switchfully.jsonbourne.infrastructure.utils.EmailUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.UUID;

public class Employee {

    private static final Logger logger = LoggerFactory.getLogger(Employee.class);

    private final UUID id;
    private final String firstname;
    private final String lastname;
    private final String email;
    private final Role role;

    public Employee( String firstname, String lastname, String email, Role role) {
        id = UUID.randomUUID();
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = validEmail(email);
        this.role = role;
    }

    public Employee(UUID id, String firstname, String lastname, String email,Role role) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = validEmail(email);
        this.role =role;
    }

    private String validEmail(String email){
        if (!EmailUtils.isValidEmail(email)){
            logger.warn("The user tried to register an invalid e-mail");
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

    public Role getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
