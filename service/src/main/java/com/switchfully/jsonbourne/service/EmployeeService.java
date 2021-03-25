package com.switchfully.jsonbourne.service;

import com.switchfully.jsonbourne.domain.models.member.Employee;
import com.switchfully.jsonbourne.domain.repository.EmployeeRepository;
import com.switchfully.jsonbourne.infrastructure.exceptions.NotAuthorizedException;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public boolean isAdmin(String uuid) {
        if (!employeeRepository.isAdmin(uuid)) {
            throw new NotAuthorizedException("you are not authorized");
        }
        return true;
    }

    public Employee addEmployee(Employee employee, String id) {
        if (isAdmin(id)) {
            employeeRepository.addEmployee(employee);
        }
        return employee;
    }

    public boolean isLibrarian(String uuid) {
        if (!employeeRepository.isLibrarian(uuid)) {
            throw new NotAuthorizedException("you are not authorized");
        }
        return true;
    }
}
