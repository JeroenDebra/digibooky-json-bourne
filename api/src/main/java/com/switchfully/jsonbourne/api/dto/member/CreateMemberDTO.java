package com.switchfully.jsonbourne.api.dto.member;

public class CreateMemberDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String inss;
    private String streetName;
    private String streetNumber;
    private String postalCode;
    private String city;

    public CreateMemberDTO() {
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

    public String getInss() {
        return inss;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public CreateMemberDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public CreateMemberDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public CreateMemberDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public CreateMemberDTO setInss(String inss) {
        this.inss = inss;
        return this;
    }

    public CreateMemberDTO setStreetName(String streetName) {
        this.streetName = streetName;
        return this;
    }

    public CreateMemberDTO setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
        return this;
    }

    public CreateMemberDTO setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public CreateMemberDTO setCity(String city) {
        this.city = city;
        return this;
    }
}
