package com.ticketing_reimbursement.net;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.ticketing_reimbursement.net.controller.ticketing_reimbursementController;
import com.ticketing_reimbursement.net.entity.Employee;
import com.ticketing_reimbursement.net.entity.tickets;
import com.ticketing_reimbursement.net.service.TicketsService;
import com.ticketing_reimbursement.net.service.employeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ticketing_reimbursementController.class)
@ExtendWith(MockitoExtension.class)
class LoginTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private employeeService employeeService1;

    @MockBean
    private TicketsService ticketsService;

    @InjectMocks
    private ticketing_reimbursementController controller;

    private ObjectMapper objectMapper;
    private Employee testEmployee;
    private tickets testTicket;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        testEmployee = new Employee();
        testEmployee.setId(1L);  // Example: Set required fields
        testEmployee.setUsername("username");
        testEmployee.setPassword("password");
    }

    @Test
    void testEmployeeLoginSuccess() throws Exception {
        when(employeeService1.loginEmployee(testEmployee)).thenReturn(testEmployee);

        mockMvc.perform(post("/Employeelogin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testEmployee)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testEmployee.getId()));
    }

    @Test
    void testEmployeeLoginFailure() throws Exception {
        when(employeeService1.loginEmployee(testEmployee)).thenReturn(null);

        mockMvc.perform(post("/Employeelogin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testEmployee)))
                .andExpect(status().isUnauthorized());
    }


}
