package com.switchfully.jsonbourne.domain.models;

import com.switchfully.jsonbourne.domain.models.member.PersonalInformation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonalInformationTest {

    @Test
    void PersonalInformationWithUnvalidEmailThrowsException() {
        assertEquals("email not valid", assertThrows(IllegalArgumentException.class, () -> new PersonalInformation("test", "test", "test", "test")).getMessage());
    }

    @Test
    void PersonalInformationWithCorrectEmailCreatesObjectWithCorrectEmail() {
        assertEquals("test@email.be", new PersonalInformation("test", "test", "test@email.be", "test").getEmail());
    }

    @Test
    void PersonalInformationWithUnvalidlastNameThrowsException() {
        assertEquals("lastname not valid",
                assertThrows(IllegalArgumentException.class, () -> new PersonalInformation("test", "     ", "test@test.be", "test")).getMessage());
    }

    @Test
    void PersonalInformationWithUnvalidINSSThrowsException() {
        assertEquals("INSS not valid",
                assertThrows(IllegalArgumentException.class, () -> new PersonalInformation("test", "test", "test@test.be", null)).getMessage());
    }
}
