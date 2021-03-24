package com.switchfully.jsonbourne.domain.repository.member;

import com.switchfully.jsonbourne.domain.models.member.Address;
import com.switchfully.jsonbourne.domain.models.member.Member;
import com.switchfully.jsonbourne.domain.models.member.PersonalInformation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocalMemberRepositoryTest {

    @Test
    void createMemberAddsOneToList() {
        //given
        LocalMemberRepository localMemberRepository = new LocalMemberRepository();
        //then
        var result = localMemberRepository.addMember(new Member(
                new PersonalInformation("test", "test", "test@test.com", "test")
                ,new Address("test", "test", "test", "Marke")));
        //when
        assertEquals(1,localMemberRepository.getsize());
    }

    @Test
    void createTheSameMemberTwiceThenDontAdd() {
        //given
        LocalMemberRepository localMemberRepository = new LocalMemberRepository();
        //then
        localMemberRepository.addMember(new Member(
                new PersonalInformation("test", "test", "test@test.com", "test")
                ,new Address("test", "test", "test", "Marke")));

        var result = localMemberRepository.addMember(new Member(
                new PersonalInformation("test", "test", "test@test.com", "test")
                ,new Address("test", "test", "test", "Marke")));
        //when

        assertEquals(1,localMemberRepository.getsize());
    }
}