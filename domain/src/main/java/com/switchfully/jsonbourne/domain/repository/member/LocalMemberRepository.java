package com.switchfully.jsonbourne.domain.repository.member;

import org.springframework.stereotype.Repository;

import java.lang.reflect.Member;
import java.util.HashSet;
import java.util.Set;

@Repository
public class LocalMemberRepository implements memberRepository {

    private final Set<Member> members = new HashSet<>();

    @Override
    public boolean addMember(Member member) {
        return members.add(member);
    }
}
