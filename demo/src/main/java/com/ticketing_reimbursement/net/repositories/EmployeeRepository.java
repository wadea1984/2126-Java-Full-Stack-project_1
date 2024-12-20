package com.ticketing_reimbursement.net.repositories;
//import org.springframework.data.repository.CrudRepository;

import com.ticketing_reimbursement.net.entity.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EmployeeRepository   extends CrudRepository<Employee, Long> {
    public Optional<Employee> findByUsername(String username);
    public Optional<Employee>findByUsernameAndPassword(String username, String Password);



}
