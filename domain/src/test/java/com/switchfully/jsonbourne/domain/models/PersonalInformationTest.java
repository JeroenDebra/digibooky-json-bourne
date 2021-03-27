package com.switchfully.jsonbourne.domain.models;

import com.switchfully.jsonbourne.domain.models.member.PersonalInformation;
import com.switchfully.jsonbourne.infrastructure.exceptions.InvalidEmailException;
import com.switchfully.jsonbourne.infrastructure.exceptions.InvalidINSSException;
import com.switchfully.jsonbourne.infrastructure.exceptions.InvalidLastNameException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonalInformationTest {

    @Test
    void PersonalInformationWithUnvalidEmailThrowsException() {
        assertEquals("E-mail not valid", assertThrows(InvalidEmailException.class, () -> new PersonalInformation("test", "test", "test", "test")).getMessage());
    }

    @Test
    void PersonalInformationWithCorrectEmailCreatesObjectWithCorrectEmail() {
        assertEquals("test@email.be", new PersonalInformation("test", "test", "test@email.be", "test").getEmail());
    }

    @Test
    void PersonalInformationWithUnvalidlastNameThrowsException() {
        assertEquals("Last name not valid",
                assertThrows(InvalidLastNameException.class, () -> new PersonalInformation("test", "     ", "test@test.be", "test")).getMessage());
    }

    @Test
    void PersonalInformationWithUnvalidINSSThrowsException() {
        assertEquals("INSS not valid",
                assertThrows(InvalidINSSException.class, () -> new PersonalInformation("test", "test", "test@test.be", null)).getMessage());
    }
}
