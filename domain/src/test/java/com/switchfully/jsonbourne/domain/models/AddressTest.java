package com.switchfully.jsonbourne.domain.models;

import com.switchfully.jsonbourne.domain.models.member.Address;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    @Test
    void createAdressCityNotValidThrowsException() {
        assertEquals("city not valid", assertThrows(IllegalArgumentException.class, () -> new Address("test", "test", "test", null)).getMessage());
    }
}