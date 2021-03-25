package com.switchfully.jsonbourne.api.dto.member;

public class CreateEmployeeDTO {

    private String firstname;
    private String lastname;
    private String email;
    private String role;

    public CreateEmployeeDTO() {
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

    public CreateEmployeeDTO setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public CreateEmployeeDTO setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public CreateEmployeeDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public CreateEmployeeDTO setRole(String role) {
        this.role = role;
        return this;
    }
}
