package com.switchfully.jsonbourne.domain.models;

import java.util.UUID;

public class Member {

    private final UUID uuid;
    private final PersonalInformation personalInformation;
    private final Address address;

    public Member(PersonalInformation personalInformation, Address address) {
        this.uuid = UUID.randomUUID();
        this.personalInformation = personalInformation;
        this.address = address;
    }

    public UUID getUuid() {
        return uuid;
    }

    public PersonalInformation getPersonalInformation() {
        return personalInformation;
    }

    public Address getAddress() {
        return address;
    }
}
