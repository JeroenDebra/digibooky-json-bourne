package com.switchfully.jsonbourne.domain.repository;

import com.switchfully.jsonbourne.domain.models.member.Member;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Repository
public class MemberRepository  {

    private final Set<Member> members = new HashSet<>();

    public void addMember(Member member) {
        members.add(member);
    }

    public Collection<Member> getAllMembers() {
        return Collections.unmodifiableSet(members);
    }

    public int getsize(){
        return members.size();
    }
}
