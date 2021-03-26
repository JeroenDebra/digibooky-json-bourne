package com.switchfully.jsonbourne.api.mappers;

import com.switchfully.jsonbourne.api.dto.member.CreateMemberDTO;
import com.switchfully.jsonbourne.api.dto.member.MemberDTO;
import com.switchfully.jsonbourne.domain.models.member.Address;
import com.switchfully.jsonbourne.domain.models.member.Member;
import com.switchfully.jsonbourne.domain.models.member.PersonalInformation;

import com.switchfully.jsonbourne.service.MemberService;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MemberMapper {

    private final MemberService memberService;

    public MemberMapper(MemberService service) {
        this.memberService = service;
    }

    private MemberDTO memberToMemberDTO(Member member){
        return new MemberDTO()
                .setId(member.getUuid())
                .setFirstName(member.getPersonalInformation().getFirstName())
                .setLastName(member.getPersonalInformation().getLastName())
                .setCity(member.getAddress().getCity())
                .setEmail(member.getPersonalInformation().getEmail())
                .setPostalCode(member.getAddress().getPostalCode())
                .setStreetName(member.getAddress().getStreetName())
                .setStreetNumber(member.getAddress().getStreetNumber())
                .setTotalAmountOfFines(member.getTotalAmountOfFines());
    }

    public List<MemberDTO> memberListToMemberDTOList(Collection<Member> members){
        return members.stream().map(this::memberToMemberDTO).collect(Collectors.toList());
    }

    public MemberDTO createMember(CreateMemberDTO createMemberDTO){
        return memberToMemberDTO(memberService.addMember(new Member(createPersonalInformation(createMemberDTO),createAddress(createMemberDTO))));
    }

    private Address createAddress(CreateMemberDTO createMemberDTO) {
        return new Address(createMemberDTO.getStreetName(), createMemberDTO.getStreetNumber(), createMemberDTO.getPostalCode(), createMemberDTO.getCity());
    }

    private PersonalInformation createPersonalInformation(CreateMemberDTO createMemberDTO) {
        return new PersonalInformation(createMemberDTO.getFirstName(), createMemberDTO.getLastName(), createMemberDTO.getEmail(), createMemberDTO.getInss());
    }

}
