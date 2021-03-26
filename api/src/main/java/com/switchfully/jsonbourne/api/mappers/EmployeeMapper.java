package com.switchfully.jsonbourne.api.mappers;

import com.switchfully.jsonbourne.api.dto.member.AuthorizationIdDTO;
import com.switchfully.jsonbourne.api.dto.member.CreateEmployeeDTO;
import com.switchfully.jsonbourne.api.dto.member.EmployeeDTO;
import com.switchfully.jsonbourne.domain.models.member.Employee;
import com.switchfully.jsonbourne.service.EmployeeService;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    private final EmployeeService employeeService;

    public EmployeeMapper(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public EmployeeDTO employeeToEmployeeDTO(Employee employee){
        return new EmployeeDTO()
                .setId(employee.getId())
                .setFirstname(employee.getFirstname())
                .setLastname(employee.getLastname())
                .setEmail(employee.getEmail())
                .setRole(employee.getRole().toString());
    }

    public EmployeeDTO createEmployee(CreateEmployeeDTO createEmployeeDTO) {
        Employee employee = new Employee(createEmployeeDTO.getFirstname(), createEmployeeDTO.getLastname(), createEmployeeDTO.getEmail(), createEmployeeDTO.getRole());
        return employeeToEmployeeDTO(employeeService.addEmployee(employee,createEmployeeDTO.getAuthorisationId()));
    }

    public String mapToStringId(AuthorizationIdDTO authorizationIdDTO) {
        return authorizationIdDTO.getAuthorizationId();
    }

}
