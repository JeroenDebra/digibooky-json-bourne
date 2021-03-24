package com.switchfully.jsonbourne.api.controllers;

import com.switchfully.jsonbourne.api.dto.member.CreateMemberDTO;
import com.switchfully.jsonbourne.api.dto.member.MemberDTO;
import com.switchfully.jsonbourne.api.mappers.MemberMapper;
import com.switchfully.jsonbourne.service.memberservice.DefaultMemberService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/member")
public class MemberController {
    private final DefaultMemberService service;
    private final MemberMapper mapper;

    public MemberController(DefaultMemberService service,MemberMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping(consumes = "application/json",produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public MemberDTO addMember(@RequestBody CreateMemberDTO createMemberDTO){
        return mapper.createMember(createMemberDTO);
    }

}
