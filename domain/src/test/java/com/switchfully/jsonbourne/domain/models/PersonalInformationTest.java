package com.switchfully.jsonbourne.domain.models;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Executable;

import static org.junit.jupiter.api.Assertions.*;

class PersonalInformationTest {

    @Test
    void PersonalInformationWithUnvalidEmailThrowsException(){
        assertEquals("email not valid", assertThrows(IllegalArgumentException.class,() -> new PersonalInformation("test","test","test","test")).getMessage());
    }

    @Test
    void PersonalInformationWithCorrectEmailCreatesObjectWithCorrectEmail() {
        assertEquals("test@email.be",new PersonalInformation("test","test","test@email.be","test"));
    }
}