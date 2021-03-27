package com.switchfully.jsonbourne.domain.models;

import com.switchfully.jsonbourne.domain.models.member.Address;
import com.switchfully.jsonbourne.infrastructure.exceptions.InvalidCityException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    @Test
    void createAdressCityNotValidThrowsException() {
        assertEquals("City not valid", assertThrows(InvalidCityException.class, () -> new Address("test", "test", "test", null)).getMessage());
    }
}