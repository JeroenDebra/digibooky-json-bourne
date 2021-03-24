package com.switchfully.jsonbourne.domain.repository.member;

import com.switchfully.jsonbourne.domain.models.member.Member;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class LocalMemberRepository implements memberRepository {

    private final Set<Member> members = new HashSet<>();

    @Override
    public Member addMember(Member member) {
        members.add(member);
        return member;
    }

    public int getsize(){
        return members.size();
    }
}
