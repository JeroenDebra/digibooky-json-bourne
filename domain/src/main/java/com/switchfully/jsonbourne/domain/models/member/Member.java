package com.switchfully.jsonbourne.domain.models.member;

import java.util.Objects;
import java.util.UUID;

public class Member {

    private final UUID uuid;
    private final PersonalInformation personalInformation;
    private final Address address;

    private double totalAmountOfFines;

    public Member(PersonalInformation personalInformation, Address address) {
        this.uuid = UUID.randomUUID();
        this.personalInformation = personalInformation;
        this.address = address;
        this.totalAmountOfFines = 0.0;
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

    public double getTotalAmountOfFines() {
        return totalAmountOfFines;
    }

    public void setTotalAmountOfFines(double totalAmountOfFines) {
        this.totalAmountOfFines += totalAmountOfFines;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return (Objects.equals(personalInformation.getEmail(),member.getPersonalInformation().getEmail())
                && Objects.equals(personalInformation.getInss(), member.getPersonalInformation().getInss()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(personalInformation.getEmail(),personalInformation.getInss());
    }
}
