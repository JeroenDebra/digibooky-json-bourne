package com.switchfully.jsonbourne.domain.models.member;

public class Address {
    private final String streetName;
    private final String streetNumber;
    private final String postalCode;
    private final String city;

    public Address(String streetName, String streetNumber, String postalCode, String city) {
        this.city = cityValidator(city);
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;

    }

    private String cityValidator(String city) {
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("city not valid");
        }
        return city;
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
}
