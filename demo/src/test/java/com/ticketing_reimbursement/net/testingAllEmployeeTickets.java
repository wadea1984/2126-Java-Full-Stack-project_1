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
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ticketing_reimbursementController.class)
@ExtendWith(MockitoExtension.class)
public class testingAllEmployeeTickets {





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
        testTicket=new tickets();
    }

    @Test
    void testAllEmployeeTicketsSuccess() throws Exception {
        List<tickets> ticketList = List.of(testTicket);
        when(ticketsService.showEmployeeTickets(anyLong())).thenReturn(ticketList);

        mockMvc.perform(get("/EmployeeTickets/{employeeID}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(testTicket.getId()));
    }

    @Test
    void testAllEmployeeTicketsFailure() throws Exception {
        when(ticketsService.showEmployeeTickets(anyLong())).thenReturn(null);

        mockMvc.perform(get("/EmployeeTickets/{employeeID}", 1L))
                .andExpect(status().isBadRequest());
    }


}

