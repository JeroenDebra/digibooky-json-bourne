package com.switchfully.jsonbourne.service.adminService;

import com.switchfully.jsonbourne.domain.models.member.Employee;

public interface EmployeeService {

    boolean isAdmin(String uuid);

    boolean isLibrarian(String uuid);
    Employee addEmployee(Employee employee);
}
