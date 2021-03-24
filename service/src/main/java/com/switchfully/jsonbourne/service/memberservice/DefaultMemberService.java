package com.switchfully.jsonbourne.service.memberservice;

import com.switchfully.jsonbourne.domain.models.member.Member;
import com.switchfully.jsonbourne.domain.repository.member.LocalMemberRepository;
import org.springframework.stereotype.Service;

@Service
public class DefaultMemberService implements MemberService{

    private final LocalMemberRepository repository;

    public DefaultMemberService(LocalMemberRepository repository) {
        this.repository = repository;
    }

    @Override
    public Member addMember(Member member) {
        return repository.addMember(member);
    }
}
