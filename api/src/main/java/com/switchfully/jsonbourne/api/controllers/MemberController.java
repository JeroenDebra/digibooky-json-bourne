package com.switchfully.jsonbourne.api.controllers;

import com.switchfully.jsonbourne.api.dto.member.CreateMemberDTO;
import com.switchfully.jsonbourne.api.dto.member.MemberDTO;
import com.switchfully.jsonbourne.api.mappers.MemberMapper;
import com.switchfully.jsonbourne.service.adminService.AdminService;
import com.switchfully.jsonbourne.service.memberservice.DefaultMemberService;
import com.switchfully.jsonbourne.service.memberservice.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/member")
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper mapper;
    private final AdminService adminService;

    public MemberController(MemberService memberService, MemberMapper mapper, AdminService adminService) {
        this.memberService = memberService;
        this.mapper = mapper;
        this.adminService = adminService;
    }

    @PostMapping(consumes = "application/json",produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public MemberDTO addMember(@RequestBody CreateMemberDTO createMemberDTO){
        return mapper.createMember(createMemberDTO);
    }


    @GetMapping(path = "/{adminId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<MemberDTO> getAllMembers(@PathVariable String adminId){
        adminService.isAdmin(adminId);

        return mapper.memberListToMemberDTOList( memberService.getAllMembers());

    }

}
