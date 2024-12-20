package com.ticketing_reimbursement.net;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ticketing_reimbursement.net.controller.ticketing_reimbursementController;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ticketing_reimbursementController.class)
@ExtendWith(MockitoExtension.class)
class createtickettest {

    @Autowired
    private MockMvc mockMvc1;

    @MockBean
    private employeeService employeeService1;

    @MockBean
    private TicketsService ticketsService;

    @InjectMocks
    private ticketing_reimbursementController controller;

    private ObjectMapper objectMapper;

    private Ticket testTicket;


    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        testTicket=new Ticket();
        testTicket.setId(1L);
        testTicket.setAmount(200);
        testTicket.setDescription("food");
        testTicket.setEmployeeID(5L);
        testTicket.setStatus("pending");
        testTicket.setName("john");
        testTicket.setDate(LocalDate.now());

    }


    @Test
    void testCreateTicketSuccess() throws Exception {
        when(ticketsService.createTickets(any(Ticket.class))).thenReturn(testTicket);

        mockMvc1.perform(post("/tickets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testTicket)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testTicket.getId()));}



    @Test
    void testCreateTicketFailure() throws Exception {
        when(ticketsService.createTickets(testTicket)).thenReturn(null);

        mockMvc1.perform(post("/tickets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testTicket)))
                .andExpect(status().isBadRequest());
    }




    @Test
    void testAllEmployeeTicketsSuccess() throws Exception {
        List<Ticket> ticketList = List.of(testTicket);
        when(ticketsService.showEmployeeTickets(anyLong())).thenReturn(ticketList);

        mockMvc1.perform(get("/EmployeeTickets/{employeeID}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(testTicket.getId()));
    }



}


