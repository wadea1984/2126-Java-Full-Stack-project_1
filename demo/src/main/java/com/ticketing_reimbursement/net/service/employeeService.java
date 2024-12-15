package com.ticketing_reimbursement.net.service;


import com.ticketing_reimbursement.net.entity.Employee;
import com.ticketing_reimbursement.net.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class employeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    public employeeService(EmployeeRepository employeeRepository){
        this.employeeRepository=employeeRepository;
    }

    public Employee registerAccount(Employee employee) {
        Optional<Employee> accountOptional =employeeRepository.findByUsername(employee.getUsername());
        if(accountOptional.isPresent()){
            return null;

        }
        if(employee.getPassword().length()<4)
            return null;

        return employeeRepository.save(employee);
    }
    public Employee loginEmployee(Employee employee) {

        Optional<Employee> employeeOptional =employeeRepository.findByUsernameAndPassword(employee.getUsername(),employee.getPassword());
        if(employeeOptional.isPresent()&&employee.getRole().equals("employee"))
            return employeeOptional.get();
        else
            return null;
    }
    public Employee loginManager(Employee employee) {

        Optional<Employee> employeeOptional =employeeRepository.findByUsernameAndPassword(employee.getUsername(),employee.getPassword());
        if(employeeOptional.isPresent()&&employee.getRole().equals("manager"))
            return employeeOptional.get();
        else
            return null;
    }


}
