package com.switchfully.jsonbourne.domain.repository;

import com.switchfully.jsonbourne.domain.models.member.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemberRepository  {

    private final Set<Member> members = new HashSet<>();

    public Member addMember(Member member) {
        members.add(member);
        return member;
    }

    public Collection<Member> getAllMembers() {
        return Collections.unmodifiableSet(members);
    }

    public int getsize(){
        return members.size();
    }

    public Optional<Member> getMemberById(String memberId){
       return members.stream().filter(member -> member.getUuid().toString().equals(memberId)).findFirst();
    }
}
