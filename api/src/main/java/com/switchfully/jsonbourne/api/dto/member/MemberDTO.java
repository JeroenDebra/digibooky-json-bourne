package com.switchfully.jsonbourne.api.dto.member;

public class MemberDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String INSS;
    private String streetName;
    private String streetNumber;
    private String postalCode;
    private String city;

    public MemberDTO() {
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

    public MemberDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public MemberDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public MemberDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public MemberDTO setINSS(String INSS) {
        this.INSS = INSS;
        return this;
    }

    public MemberDTO setStreetName(String streetName) {
        this.streetName = streetName;
        return this;
    }

    public MemberDTO setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
        return this;
    }

    public MemberDTO setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public MemberDTO setCity(String city) {
        this.city = city;
        return this;
    }
}
