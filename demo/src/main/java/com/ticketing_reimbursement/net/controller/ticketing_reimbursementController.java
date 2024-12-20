package com.ticketing_reimbursement.net.controller;

import com.ticketing_reimbursement.net.entity.Employee;
import com.ticketing_reimbursement.net.entity.tickets;
import com.ticketing_reimbursement.net.service.TicketsService;
import com.ticketing_reimbursement.net.service.employeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ticketing_reimbursementController {

    private static final Logger logger = LoggerFactory.getLogger(ticketing_reimbursementController.class);  // Logger initialization

    private final employeeService employeeService1;
    private final TicketsService ticketsService;

    public ticketing_reimbursementController(employeeService employeeService1, TicketsService ticketsService) {
        this.employeeService1 = employeeService1;
        this.ticketsService = ticketsService;
    }

    @PostMapping("register")
    public ResponseEntity<Employee> register(@RequestBody Employee employee) {
        logger.info("Attempting to register employee: {}", employee);  // Log employee registration attempt
        Employee registerAccount = employeeService1.registerAccount(employee);
        if (registerAccount != null) {
            logger.info("Employee registered successfully: {}", registerAccount);  // Log successful registration
            return ResponseEntity.ok(registerAccount);
        } else {
            logger.error("Registration failed for employee: {}", employee);  // Log failed registration
            return ResponseEntity.status(409).body(null);
        }
    }

    @PostMapping("Employeelogin")
    public ResponseEntity<Employee> employeeLogin(@RequestBody Employee employee) {
        logger.info("Employee login attempt: {}", employee);  // Log login attempt
        Employee login = employeeService1.loginEmployee(employee);
        if (login != null) {
            logger.info("Employee login successful: {}", login);  // Log successful login
            return ResponseEntity.ok(login);
        } else {
            logger.warn("Employee login failed: {}", employee);  // Log failed login
            return ResponseEntity.status(401).body(null);
        }
    }

    @PostMapping("Managerlogin")
    public ResponseEntity<Employee> managerLogin(@RequestBody Employee employee) {
        logger.info("Manager login attempt: {}", employee);  // Log manager login attempt
        Employee login = employeeService1.loginManager(employee);
        if (login != null) {
            logger.info("Manager login successful: {}", login);  // Log successful manager login
            return ResponseEntity.ok(login);
        } else {
            logger.warn("Manager login failed: {}", employee);  // Log failed manager login
            return ResponseEntity.status(401).body(null);
        }
    }

    @PostMapping("tickets")
    public ResponseEntity<tickets> createtickets(@RequestBody tickets tickets1) {
        logger.info("Creating new ticket: {}", tickets1);  // Log ticket creation attempt
        tickets newticket = ticketsService.createTickets(tickets1);
        if (newticket != null) {
            logger.info("Ticket created successfully: {}", newticket);  // Log successful ticket creation
            return ResponseEntity.ok(newticket);
        } else {
            logger.error("Failed to create ticket: {}", tickets1);  // Log failed ticket creation
            return ResponseEntity.status(400).body(null);
        }
    }

    @GetMapping("EmployeeTickets/{employeeID}")
    public ResponseEntity<List<tickets>> AllEmployeetickets(@PathVariable Long employeeID) {
        logger.info("Fetching tickets for employee ID: {}", employeeID);  // Log ticket fetch attempt
        List<tickets> ticketsList = ticketsService.showEmployeeTickets(employeeID);
        if (ticketsList != null) {
            logger.info("Fetched {} tickets for employee ID: {}", ticketsList.size(), employeeID);  // Log successful ticket fetch
            return ResponseEntity.ok(ticketsList);
        } else {
            logger.warn("No tickets found for employee ID: {}", employeeID);  // Log no tickets found
            return ResponseEntity.status(400).body(null);
        }
    }

    @GetMapping("PendingTickets")
    public ResponseEntity<List<tickets>> ShowAllPending() {
        logger.info("Fetching all pending tickets");  // Log pending tickets fetch attempt
        List<tickets> ticketsList = ticketsService.showPendingTickets();
        if (ticketsList != null) {
            logger.info("Fetched {} pending tickets", ticketsList.size());  // Log successful pending tickets fetch
            return ResponseEntity.ok(ticketsList);
        } else {
            logger.warn("No pending tickets found");  // Log no pending tickets found
            return ResponseEntity.status(400).body(null);
        }
    }

    @PatchMapping("tickets/{ticketsId}")
    public ResponseEntity<Integer> UpdateTickets(@PathVariable Integer ticketsId, @RequestBody tickets tickets1) {
        logger.info("Attempting to update ticket with ID: {}", ticketsId);  // Log ticket update attempt
        Long i = Long.valueOf(ticketsId);
        int confirmation = ticketsService.UpdatedticketById(i, tickets1);
        if (confirmation == 1) {
            logger.info("Ticket ID: {} updated successfully", ticketsId);  // Log successful ticket update
            return ResponseEntity.ok(confirmation);
        } else {
            logger.error("Failed to update ticket with ID: {}", ticketsId);  // Log failed ticket update
            return ResponseEntity.status(400).body(null);
        }
    }
}


