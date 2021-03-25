package com.switchfully.jsonbourne.service.adminService;

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
    public boolean isLibrarian(String uuid) {
        if (!employeeRepository.isLibrarian(uuid)) {
            throw new NotAuthorizedException("you are not authorized");
        }
        return true;
    }
}
