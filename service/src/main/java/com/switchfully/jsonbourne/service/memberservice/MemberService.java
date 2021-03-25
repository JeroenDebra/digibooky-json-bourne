package com.switchfully.jsonbourne.service.memberservice;

import com.switchfully.jsonbourne.domain.models.member.Member;

import java.util.Collection;

public interface MemberService {
    Member addMember(Member member);
    Collection<Member> getAllMembers();
}
