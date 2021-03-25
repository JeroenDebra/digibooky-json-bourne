package com.switchfully.jsonbourne.api.controllers;

import com.switchfully.jsonbourne.api.dto.member.CreateEmployeeDTO;
import com.switchfully.jsonbourne.api.dto.member.EmployeeDTO;
import com.switchfully.jsonbourne.api.mappers.EmployeeMapper;
import com.switchfully.jsonbourne.service.employeeservice.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    private final EmployeeMapper employeeMapper;
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeMapper employeeMapper, EmployeeService employeeService) {
        this.employeeMapper = employeeMapper;
        this.employeeService = employeeService;
    }

    @PostMapping(path = "/addLibrarian/{adminId}",consumes = "application/json",produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDTO addLibrarian(@RequestBody CreateEmployeeDTO createEmployeeDTO, @PathVariable String adminId){
        logger.info("An admin tried to register a new librarian to the employee database");
        return employeeMapper.employeeToEmployeeDTO(employeeService.addEmployee(employeeMapper.createLibrarian(createEmployeeDTO), adminId));
    }

    @PostMapping(path = "/addAdmin/{adminId}",consumes = "application/json",produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDTO addAdmin(@RequestBody CreateEmployeeDTO createEmployeeDTO,@PathVariable String adminId){
        logger.info("An admin tried to register a new admin to the employee database");
        return employeeMapper.employeeToEmployeeDTO(employeeService.addEmployee(employeeMapper.createAdmin(createEmployeeDTO), adminId));
    }
}
