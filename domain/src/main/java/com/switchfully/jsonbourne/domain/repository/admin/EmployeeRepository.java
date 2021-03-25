package com.switchfully.jsonbourne.domain.repository.admin;

public interface EmployeeRepository {

    boolean isAdmin(String uuuid);

    boolean isLibrarian(String uuid);
}
