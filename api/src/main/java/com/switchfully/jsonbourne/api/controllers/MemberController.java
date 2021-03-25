package com.switchfully.jsonbourne.api.controllers;

import com.switchfully.jsonbourne.api.dto.member.CreateMemberDTO;
import com.switchfully.jsonbourne.api.dto.member.MemberDTO;
import com.switchfully.jsonbourne.api.mappers.MemberMapper;
import com.switchfully.jsonbourne.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/member")
public class MemberController {

    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    private final MemberService memberService;
    private final MemberMapper mapper;

    public MemberController(MemberService memberService, MemberMapper mapper) {
        this.memberService = memberService;
        this.mapper = mapper;
    }

    @PostMapping(consumes = "application/json",produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public MemberDTO addMember(@RequestBody CreateMemberDTO createMemberDTO){
        logger.info("A user is requesting to create a new member account");
        return mapper.createMember(createMemberDTO);
    }

    @GetMapping(path = "/{adminId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<MemberDTO> getAllMembers(@PathVariable String adminId){
        logger.info("An Admin requests a list of all the members");
        return mapper.memberListToMemberDTOList(memberService.getAllMembers(adminId));
    }

}
