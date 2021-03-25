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
        return employeeRepository.isAdmin(uuid);
    }

    public Employee addEmployee(Employee employee, String id) {
        if (!isAdmin(id)) {
            throw new NotAuthorizedException("this user may not add another employee");
        }
        employeeRepository.addEmployee(employee);
        return employee;
    }

    public boolean isLibrarian(String uuid) {
        return employeeRepository.isLibrarian(uuid);
    }
}
