package com.switchfully.jsonbourne.domain.repository.member;

import com.switchfully.jsonbourne.domain.models.member.Address;
import com.switchfully.jsonbourne.domain.models.member.Member;
import com.switchfully.jsonbourne.domain.models.member.PersonalInformation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocalMemberRepositoryTest {

    private final LocalMemberRepository localMemberRepository = new LocalMemberRepository();

    @Test
    void createMemberAddsOneToList() {
        localMemberRepository.addMember(new Member(
                new PersonalInformation("test", "test", "test@test.com", "test")
                ,new Address("test", "test", "test", "Marke")));
        assertEquals(1,localMemberRepository.getsize());
    }

    @Test
    void createTheSameMemberTwiceThenDontAdd() {
        localMemberRepository.addMember(new Member(
                new PersonalInformation("test", "test", "test@test.com", "test")
                ,new Address("test", "test", "test", "Marke")));
        localMemberRepository.addMember(new Member(
                new PersonalInformation("test", "test", "test@test.com", "test")
                ,new Address("test", "test", "test", "Marke")));
        assertEquals(1,localMemberRepository.getsize());
    }
}