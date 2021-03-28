package com.switchfully.jsonbourne.service;

import com.switchfully.jsonbourne.domain.models.employee.Employee;
import com.switchfully.jsonbourne.domain.repository.EmployeeRepository;
import com.switchfully.jsonbourne.infrastructure.exceptions.NotAuthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public boolean isAdmin(String uuid) {
        return employeeRepository.isAdmin(uuid);
    }

    public Employee addEmployee(Employee employee, String id) {
        if (!isAdmin(id)) {
            logger.warn("This user tried to register a new employee without the right permissions");
            throw new NotAuthorizedException("this user may not add another employee");
        }
        return employeeRepository.addEmployee(employee);
    }

    public boolean isLibrarian(String uuid) {
        return employeeRepository.isLibrarian(uuid);
    }
}
