package com.switchfully.jsonbourne.domain.repository.member;

import com.switchfully.jsonbourne.domain.models.member.Address;
import com.switchfully.jsonbourne.domain.models.member.Member;
import com.switchfully.jsonbourne.domain.models.member.PersonalInformation;
import com.switchfully.jsonbourne.domain.repository.MemberRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocalMemberRepositoryTest {

    private final MemberRepository memberRepository = new MemberRepository();

    @Test
    void createMemberAddsOneToList() {
        memberRepository.addMember(new Member(
                new PersonalInformation("test", "test", "test@test.com", "test")
                ,new Address("test", "test", "test", "Marke")));
        assertEquals(1, memberRepository.getsize());
    }

    @Test
    void createTheSameMemberTwiceThenDontAdd() {
        memberRepository.addMember(new Member(
                new PersonalInformation("test", "test", "test@test.com", "test")
                ,new Address("test", "test", "test", "Marke")));
        memberRepository.addMember(new Member(
                new PersonalInformation("test", "test", "test@test.com", "test")
                ,new Address("test", "test", "test", "Marke")));
        assertEquals(1, memberRepository.getsize());
    }
}