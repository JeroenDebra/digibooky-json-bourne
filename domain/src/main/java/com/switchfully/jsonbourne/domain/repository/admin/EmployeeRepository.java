package com.switchfully.jsonbourne.domain.repository.admin;

import com.switchfully.jsonbourne.domain.models.member.Employee;

public interface EmployeeRepository {

    boolean isAdmin(String uuuid);
    boolean addEmployee(Employee employee);
}
