package com.switchfully.jsonbourne.service.adminService;

public interface EmployeeService {

    boolean isAdmin(String uuid);

    boolean isLibrarian(String uuid);
}
