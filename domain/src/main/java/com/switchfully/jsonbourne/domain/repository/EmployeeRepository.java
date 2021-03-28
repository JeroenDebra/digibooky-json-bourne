package com.switchfully.jsonbourne.domain.repository;

import com.switchfully.jsonbourne.domain.models.member.Employee;
import com.switchfully.jsonbourne.domain.models.member.Role;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Repository
public class EmployeeRepository {

    private final Set<Employee> employees = new HashSet<>();

    public EmployeeRepository() {
       this.init();
    }

    private void init(){
        employees.add(new Employee(UUID.fromString("d774eceb-03c5-4f63-9e92-aa1025b2257f"),"","","admin@digibooky.be", Role.ADMIN));
        employees.add(new Employee(UUID.fromString("ca212169-3f51-4eda-a351-b57abc517eda"),"lib","lib","lib@digibooky.be", Role.LIBRARIAN));
    }

    public boolean isAdmin(String uuid) {
        return employees.stream().filter(employee -> employee.getRole() == Role.ADMIN).filter(employee -> employee.getId().toString().equals(uuid)).count() > 0;
    }

    public boolean isLibrarian(String uuid) {
        return employees.stream().filter(employee -> employee.getRole() == Role.LIBRARIAN).filter(employee -> employee.getId().toString().equals(uuid)).count() > 0;
    }

    public Employee addEmployee(Employee employee) {
        employees.add(employee);
        return  employee;
    }
}
