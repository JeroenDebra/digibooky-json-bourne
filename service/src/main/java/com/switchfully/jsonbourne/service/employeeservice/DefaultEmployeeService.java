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
        return employeeRepository.isAdmin(uuid);
    }

    @Override
    public boolean isLibrarian(String uuid) {
        return employeeRepository.isLibrarian(uuid);
    }

    @Override
    public Employee addEmployee(Employee employee, String id) {
        if (!isAdmin(id)) throw new NotAuthorizedException("users does not have permission to add employees");

        employeeRepository.addEmployee(employee);
        return employee;
    }


}
