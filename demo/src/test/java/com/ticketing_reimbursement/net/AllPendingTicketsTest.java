package com.ticketing_reimbursement.net;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ticketing_reimbursement.net.controller.ticketing_reimbursementController;
import com.ticketing_reimbursement.net.entity.Employee;
import com.ticketing_reimbursement.net.entity.Ticket;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ticketing_reimbursementController.class)
@ExtendWith(MockitoExtension.class)
public class AllPendingTicketsTest {


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
    private Ticket testTicket;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        testEmployee = new Employee();
        testTicket=new Ticket();
    }

    @Test
    void testShowAllPendingTicketsSuccess() throws Exception {
        List<Ticket> ticketList = List.of(testTicket);
        when(ticketsService.showPendingTickets()).thenReturn(ticketList);

        mockMvc.perform(get("/PendingTickets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(testTicket.getId()));
    }

    @Test
    void testShowAllPendingTicketsFailure() throws Exception {
        when(ticketsService.showPendingTickets()).thenReturn(null);

        mockMvc.perform(get("/PendingTickets"))
                .andExpect(status().isBadRequest());
    }


}