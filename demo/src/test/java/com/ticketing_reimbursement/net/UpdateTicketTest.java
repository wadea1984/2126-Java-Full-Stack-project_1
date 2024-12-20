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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ticketing_reimbursementController.class)
@ExtendWith(MockitoExtension.class)
class UpdateTicketTest {

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
        testTicket = new tickets();
        testTicket.setId(1L);
        testTicket.setAmount(100);
        testTicket.setDescription("Test description");
        testTicket.setEmployeeID(5L);
        testTicket.setStatus("pending");
        testTicket.setName("John Doe");
    }

   @Test
    void testUpdateTicketSuccess() throws Exception {
        when(ticketsService.UpdatedticketById(anyLong(), any(tickets.class))).thenReturn(1);

       mockMvc.perform(patch("/tickets/{ticketsId}")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(objectMapper.writeValueAsString(testEmployee)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").value(testEmployee.getId()));
    }

    @Test
    void testUpdateTicketFailure() throws Exception {
        when(ticketsService.UpdatedticketById(anyLong(), any(tickets.class))).thenReturn(0);

        mockMvc.perform(patch("/tickets/{ticketsId}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testTicket)))
                .andExpect(status().isBadRequest());
    }

}
