package com.switchfully.jsonbourne.domain.models.member;

import com.switchfully.jsonbourne.infrastructure.exceptions.InvalidCityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Address {

    private static final Logger logger = LoggerFactory.getLogger(Address.class);

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
            logger.warn("The user tried to register an invalid city");
            throw new InvalidCityException("City not valid");
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
