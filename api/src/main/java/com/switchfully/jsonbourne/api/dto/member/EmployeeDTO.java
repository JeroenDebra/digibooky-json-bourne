package com.switchfully.jsonbourne.api.dto.member;

import java.util.UUID;

public class EmployeeDTO {

    private UUID id;
    private String firstname;
    private String lastname;
    private String email;
    private String role;

    public EmployeeDTO() {
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

    public String getRole() {
        return role;
    }

    public UUID getId() {
        return id;
    }

    public EmployeeDTO setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public EmployeeDTO setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public EmployeeDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public EmployeeDTO setRole(String role) {
        this.role = role;
        return this;
    }

    public EmployeeDTO setId(UUID id) {
        this.id = id;
        return this;
    }
}
