package com.ticketing_reimbursement.net.controller;


import com.ticketing_reimbursement.net.entity.Employee;
import com.ticketing_reimbursement.net.entity.tickets;
import com.ticketing_reimbursement.net.service.TicketsService;
import com.ticketing_reimbursement.net.service.employeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ticketing_reimbursementController {
    private employeeService employeeService1;
    private TicketsService ticketsService;

    public ticketing_reimbursementController(employeeService employeeService1,TicketsService ticketsService){
        this.employeeService1=employeeService1;
        this.ticketsService=ticketsService;
    }
    @PostMapping("register")
    public ResponseEntity<Employee> register(@RequestBody Employee employee) {
            Employee registerAccount=employeeService1.registerAccount(employee);
            if(registerAccount!=null)
              return ResponseEntity.ok(registerAccount);
            else
                return ResponseEntity.status(409).body(null);

    }
    @PostMapping("Employeelogin")
    public ResponseEntity<Employee> employeeLogin(@RequestBody Employee employee) {

            Employee login=employeeService1.loginEmployee(employee);
            if(login!=null)
             return ResponseEntity.ok(login);
            else
              return ResponseEntity.status(401).body(null);

    }
    @PostMapping("Managerlogin")
    public ResponseEntity<Employee> managerLogin(@RequestBody Employee employee) {

        Employee login=employeeService1.loginManager(employee);
        if(login!=null)
            return ResponseEntity.ok(login);
        else
            return ResponseEntity.status(401).body(null);

    }
    @PostMapping("tickets")
    public ResponseEntity<tickets> createtickets(@RequestBody tickets tickets1){
            tickets newticket=ticketsService.createTickets(tickets1);
            if(newticket!=null)
               return ResponseEntity.ok(newticket);
            else
                return ResponseEntity.status(400).body(null);
    }
    @GetMapping("EmployeeTickets/{employeeID}")
    public ResponseEntity<List<tickets>> AllEmployeetickets(@PathVariable Long employeeID){
        List<tickets>ticketsList= ticketsService.showEmployeeTickets(employeeID);
        if(ticketsList!=null)
          return  ResponseEntity.ok(ticketsList);
        else
            return ResponseEntity.status(400).body(null);
    }
    @GetMapping("PendingTickets")
    public ResponseEntity<List<tickets>> ShowAllPending(){
        List<tickets>ticketsList= ticketsService.showPendingTickets();
        if(ticketsList!=null)
            return  ResponseEntity.ok(ticketsList);
        else
            return ResponseEntity.status(400).body(null);
    }

    @PatchMapping("tickets/{ticketsId}")
    public ResponseEntity<Integer> UpdateTickets(@PathVariable Integer ticketsId, @RequestBody tickets tickets1){

            Long i=Long.valueOf(ticketsId);
            int conformation=ticketsService.UpdatedticketById(i,tickets1);
            if(conformation==1)
              return ResponseEntity.ok(conformation);

            else
              return ResponseEntity.status(400).body(null);
        }
    }




