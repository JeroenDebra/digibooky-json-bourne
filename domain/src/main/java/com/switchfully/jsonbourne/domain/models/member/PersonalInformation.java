package com.switchfully.jsonbourne.domain.models.member;

import com.switchfully.jsonbourne.infrastructure.exceptions.InvalidEmailException;
import com.switchfully.jsonbourne.infrastructure.exceptions.InvalidINSSException;
import com.switchfully.jsonbourne.infrastructure.exceptions.InvalidLastNameException;
import com.switchfully.jsonbourne.infrastructure.utils.EmailUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PersonalInformation {

    private static final Logger logger = LoggerFactory.getLogger(PersonalInformation.class);

    private final String firstName;
    private final String lastName;
    private final String email;
    private final String inss;

    public PersonalInformation(String firstName, String lastName, String email, String inss) {
        this.firstName = firstName;
        this.lastName = lastNameValidator(lastName);
        this.email = emailValidator(email);
        this.inss = inssValidator(inss);
    }

    private String lastNameValidator(String lastName) {
        if (lastName == null || lastName.isBlank()) {
            logger.warn("The user tried to register an invalid last name");
            throw new InvalidLastNameException("Last name not valid");
        }
        return lastName;
    }

    private String emailValidator(String email) {
        if (!EmailUtils.isValidEmail(email)) {
            logger.warn("The user tried to register an invalid e-mail");
            throw new InvalidEmailException("E-mail not valid");
        }
        return email;
    }

    private String inssValidator(String inss){
        if (inss == null || inss.isBlank()) {
            logger.warn("The user tried to register an invalid INSS");
            throw new InvalidINSSException("INSS not valid");
        }
        return inss;
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
}
