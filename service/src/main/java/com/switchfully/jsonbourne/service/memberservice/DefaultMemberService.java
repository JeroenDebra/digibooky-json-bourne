package com.switchfully.jsonbourne.service.memberservice;

import com.switchfully.jsonbourne.domain.models.member.Member;
import com.switchfully.jsonbourne.domain.repository.member.LocalMemberRepository;
import com.switchfully.jsonbourne.infrastructure.exceptions.DuplicateMemberException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultMemberService implements MemberService{

    private final LocalMemberRepository repository;

    public DefaultMemberService(LocalMemberRepository repository) {
        this.repository = repository;
    }

    @Override
    public Member addMember(Member newMember) {
        if (!checkIfDuplicateExists(newMember)){
            throw new DuplicateMemberException("This member already exists");
        }
        repository.addMember(newMember);
        return newMember;
    }

    private boolean checkIfDuplicateExists(Member newMember) {
        return repository.getAllMembers().stream()
                .filter(member -> member.getPersonalInformation().getEmail().equalsIgnoreCase(newMember.getPersonalInformation().getEmail().toLowerCase()) || member.getPersonalInformation().getInss().equals(newMember.getPersonalInformation().getInss()))
                .collect(Collectors.toList()).isEmpty();
    }

    @Override
    public Collection<Member> getAllMembers() {
        return repository.getAllMembers();
    }
}
