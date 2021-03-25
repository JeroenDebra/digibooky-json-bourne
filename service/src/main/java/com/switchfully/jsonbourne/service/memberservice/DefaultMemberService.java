package com.switchfully.jsonbourne.service.memberservice;

import com.switchfully.jsonbourne.domain.models.member.Member;
import com.switchfully.jsonbourne.domain.repository.member.MemberRepository;
import com.switchfully.jsonbourne.infrastructure.exceptions.DuplicateMemberException;
import com.switchfully.jsonbourne.infrastructure.exceptions.NotAuthorizedException;
import com.switchfully.jsonbourne.service.employeeservice.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class DefaultMemberService implements MemberService{

    private final MemberRepository memberRepository;
    private final EmployeeService employeeService;

    public DefaultMemberService(MemberRepository memberRepository, EmployeeService employeeService) {
        this.memberRepository = memberRepository;
        this.employeeService = employeeService;
    }

    @Override
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

    @Override
    public Collection<Member> getAllMembers(String adminId) {
        if (employeeService.isAdmin(adminId)) throw new NotAuthorizedException("user does not have permission to view user data");

        return memberRepository.getAllMembers();
    }
}
