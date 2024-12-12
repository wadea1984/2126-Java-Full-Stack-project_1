package com.ticketing_reimbursement.net.repositories;

import com.ticketing_reimbursement.net.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    public Optional<Employee> findByUsername(String username);
    public Optional<Employee>findByUsernameAndPassword(String username, String Password);

}
