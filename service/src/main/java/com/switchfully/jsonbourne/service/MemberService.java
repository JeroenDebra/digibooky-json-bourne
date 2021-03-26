package com.switchfully.jsonbourne.service;

import com.switchfully.jsonbourne.domain.models.member.Member;
import com.switchfully.jsonbourne.domain.repository.MemberRepository;
import com.switchfully.jsonbourne.infrastructure.exceptions.DuplicateMemberException;
import com.switchfully.jsonbourne.infrastructure.exceptions.MemberNotFoundException;
import com.switchfully.jsonbourne.infrastructure.exceptions.NotAuthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MemberService {

    private static final Logger logger = LoggerFactory.getLogger(MemberService.class);

    private final MemberRepository memberRepository;
    private final EmployeeService employeeService;

    public MemberService(MemberRepository memberRepository, EmployeeService employeeService) {
        this.memberRepository = memberRepository;
        this.employeeService = employeeService;
    }

    public Member addMember(Member newMember) {
        if (!checkIfDuplicateExists(newMember)) {
            logger.warn("The user tried to register a user that already exists");
            throw new DuplicateMemberException("This member already exists");
        }
        memberRepository.addMember(newMember);
        return newMember;
    }

    private boolean checkIfDuplicateExists(Member newMember) {
        return memberRepository.getAllMembers().stream().filter(member -> member.getPersonalInformation().getEmail().equalsIgnoreCase(newMember.getPersonalInformation().getEmail().toLowerCase()) || member.getPersonalInformation().getInss().equals(newMember.getPersonalInformation().getInss())).collect(Collectors.toList()).isEmpty();
    }

    public Collection<Member> getAllMembers(String adminId) {
        if (!employeeService.isAdmin(adminId)) {
            logger.warn("This user tried to see all the members without the right permissions");
            throw new NotAuthorizedException("User has no permission to view member data");
        }
        return memberRepository.getAllMembers();
    }
}
