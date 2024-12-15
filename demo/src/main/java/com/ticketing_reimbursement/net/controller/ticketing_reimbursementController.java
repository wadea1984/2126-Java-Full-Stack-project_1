package com.ticketing_reimbursement.net.controller;


import com.ticketing_reimbursement.net.entity.Employee;
import com.ticketing_reimbursement.net.service.employeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ticketing_reimbursementController {
    private employeeService employeeService1;

    public ticketing_reimbursementController(employeeService employeeService1){
        this.employeeService1=employeeService1;
    }
    @PostMapping("register")
    public ResponseEntity<Employee> register(@RequestBody Employee employee) {
            Employee registerAccount=employeeService1.registerAccount(employee);
            if(registerAccount!=null)
              return ResponseEntity.ok(registerAccount);
            else
                return ResponseEntity.status(409).body(null);

    }
}
