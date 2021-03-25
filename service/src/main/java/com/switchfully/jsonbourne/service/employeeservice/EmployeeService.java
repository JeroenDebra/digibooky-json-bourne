package com.switchfully.jsonbourne.service.employeeservice;

import com.switchfully.jsonbourne.domain.models.member.Employee;

public interface EmployeeService {

    boolean isAdmin(String uuid);

    boolean isLibrarian(String uuid);
    Employee addEmployee(Employee employee, String id);
}
