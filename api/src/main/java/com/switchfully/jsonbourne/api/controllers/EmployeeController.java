package com.switchfully.jsonbourne.api.controllers;

import com.switchfully.jsonbourne.api.dto.member.CreateEmployeeDTO;
import com.switchfully.jsonbourne.api.dto.member.EmployeeDTO;
import com.switchfully.jsonbourne.api.mappers.EmployeeMapper;
import com.switchfully.jsonbourne.service.adminService.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    public EmployeeController(EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @PostMapping(path = "/addLibrarian/{adminId}",consumes = "application/json",produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDTO addLibrarian(@RequestBody CreateEmployeeDTO createEmployeeDTO,@PathVariable String adminId){
        return employeeMapper.employeeToEmployeeDTO(employeeService.addEmployee(employeeMapper.createLibrarian(createEmployeeDTO), adminId));
    }

    @PostMapping(path = "/addAdmin/{adminId}",consumes = "application/json",produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDTO addAdmin(@RequestBody CreateEmployeeDTO createEmployeeDTO,@PathVariable String adminId){
        return employeeMapper.employeeToEmployeeDTO(employeeService.addEmployee(employeeMapper.createAdmin(createEmployeeDTO), adminId));
    }
}
