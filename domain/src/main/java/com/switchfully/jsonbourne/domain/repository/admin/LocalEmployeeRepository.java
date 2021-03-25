package com.switchfully.jsonbourne.domain.repository.admin;

import com.switchfully.jsonbourne.domain.models.member.Employee;
import com.switchfully.jsonbourne.domain.models.member.Role;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Repository
public class LocalEmployeeRepository implements EmployeeRepository {

    private final Set<Employee> employees = new HashSet<>();


    public LocalEmployeeRepository() {
       this.init();
    }

    private void init(){
        employees.add(new Employee(UUID.fromString("d774eceb-03c5-4f63-9e92-aa1025b2257f"),"","","admin@digibooky.be", Role.ADMIN));
    }

    @Override
    public boolean isAdmin(String uuuid) {
        return employees.stream().filter(employee -> employee.getRole() == Role.ADMIN).filter(employee -> employee.getId().toString().equals(uuuid)).count() > 0;
    }

    @Override
    public boolean addEmployee(Employee employee) {
        return employees.add(employee);
    }
}
