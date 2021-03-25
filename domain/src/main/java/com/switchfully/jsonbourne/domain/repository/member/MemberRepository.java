package com.switchfully.jsonbourne.domain.repository.member;

import com.switchfully.jsonbourne.domain.models.member.Member;

import java.util.Collection;

public interface MemberRepository {

    void addMember(Member member);
    Collection<Member> getAllMembers();
}
