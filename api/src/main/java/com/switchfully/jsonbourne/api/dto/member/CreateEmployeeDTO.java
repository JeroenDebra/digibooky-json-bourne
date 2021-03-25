package com.switchfully.jsonbourne.api.dto.member;

import com.switchfully.jsonbourne.domain.models.member.Role;

public class CreateEmployeeDTO {

    private String authorisationId;
    private String firstname;
    private String lastname;
    private String email;
    private Role role;

    public CreateEmployeeDTO() {
    }

    public String getAuthorisationId() {
        return authorisationId;
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

    public void setRole(Role role) {
        this.role = role;
    }

    public void setAuthorisationId(String authorisationId) {
        this.authorisationId = authorisationId;
    }
}
