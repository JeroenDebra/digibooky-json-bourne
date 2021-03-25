package com.switchfully.jsonbourne.api.mappers;

import com.switchfully.jsonbourne.api.dto.member.CreateEmployeeDTO;
import com.switchfully.jsonbourne.api.dto.member.EmployeeDTO;
import com.switchfully.jsonbourne.domain.models.member.Employee;
import com.switchfully.jsonbourne.service.adminService.EmployeeService;
import org.springframework.stereotype.Component;
import com.switchfully.jsonbourne.domain.models.member.Role;


@Component
public class EmployeeMapper {

    private final EmployeeService employeeToEmployeeDTO;

    public EmployeeMapper(EmployeeService employeeToEmployeeDTO) {
        this.employeeToEmployeeDTO = employeeToEmployeeDTO;
    }

    private EmployeeDTO employeeToEmployeeDTO(Employee employee){
        return new EmployeeDTO()
                .setId(employee.getId())
                .setFirstname(employee.getFirstname())
                .setLastname(employee.getLastname())
                .setEmail(employee.getEmail())
                .setRole(employee.getRole().toString());
    }

    private Role roleMapper(String role){
        if (role.equals("admin")){
            return Role.ADMIN;
        }
        return Role.LIBRARIAN;
    }

    public EmployeeDTO createEmployee(CreateEmployeeDTO createEmployeeDTO) {
        Employee employee = new Employee(createEmployeeDTO.getFirstname(), createEmployeeDTO.getLastname(), createEmployeeDTO.getEmail(), roleMapper(createEmployeeDTO.getRole()));

        return employeeToEmployeeDTO(employeeToEmployeeDTO.addEmployee(employee));
    }
}