package com.switchfully.jsonbourne.service.employeeservice;

import com.switchfully.jsonbourne.domain.models.member.Employee;
import com.switchfully.jsonbourne.domain.repository.admin.EmployeeRepository;
import com.switchfully.jsonbourne.infrastructure.exceptions.NotAuthorizedException;
import org.springframework.stereotype.Service;

@Service
public class DefaultEmployeeService implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public DefaultEmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public boolean isAdmin(String uuid) {
        if (!employeeRepository.isAdmin(uuid)) {
            throw new NotAuthorizedException("you are not authorized");
        }
        return true;
    }

    @Override
    public Employee addEmployee(Employee employee, String id) {
        if (isAdmin(id)) {
            employeeRepository.addEmployee(employee);
        }
        return employee;
    }

    @Override
    public boolean isLibrarian(String uuid) {
        if (!employeeRepository.isLibrarian(uuid)) {
            throw new NotAuthorizedException("you are not authorized");
        }
        return true;
    }
}
