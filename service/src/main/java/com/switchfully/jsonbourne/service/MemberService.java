package com.switchfully.jsonbourne.service;

import com.switchfully.jsonbourne.domain.models.member.Member;
import com.switchfully.jsonbourne.domain.repository.MemberRepository;
import com.switchfully.jsonbourne.infrastructure.exceptions.DuplicateMemberException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class MemberService{

    private final MemberRepository memberRepository;
    private final EmployeeService employeeService;

    public MemberService(MemberRepository memberRepository, EmployeeService employeeService) {
        this.memberRepository = memberRepository;
        this.employeeService = employeeService;
    }

    public Member addMember(Member newMember) {
        if (!checkIfDuplicateExists(newMember)){
            throw new DuplicateMemberException("This member already exists");
        }
        memberRepository.addMember(newMember);
        return newMember;
    }

    private boolean checkIfDuplicateExists(Member newMember) {
        return memberRepository.getAllMembers().stream()
                .filter(member -> member.getPersonalInformation().getEmail().equalsIgnoreCase(newMember.getPersonalInformation().getEmail().toLowerCase()) || member.getPersonalInformation().getInss().equals(newMember.getPersonalInformation().getInss()))
                .collect(Collectors.toList()).isEmpty();
    }

    public Collection<Member> getAllMembers(String admin) {
        employeeService.isAdmin(admin);
        return memberRepository.getAllMembers();
    }
}
