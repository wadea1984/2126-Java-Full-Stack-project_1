package com.ticketing_reimbursement.net.service;


import com.ticketing_reimbursement.net.entity.tickets;
import com.ticketing_reimbursement.net.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketsService {
    @Autowired
    TicketRepository ticketsRepository;

    public TicketsService(TicketRepository ticketsRepository) {
        this.ticketsRepository = ticketsRepository;
    }
    public tickets createTickets(tickets tickets1){
        if(tickets1.getDescription().length()<1)
            return null;

        if(tickets1.getDescription().length()>255)
            return null;
        return ticketsRepository.save(tickets1);
    }

    public tickets showPendingTickets(tickets tickets2){
        Optional<tickets> ticketsOptional=ticketsRepository.findByStatus("pending");
        return ticketsOptional.orElse(null);

    }
    public tickets showEmoplyeeTickets(tickets tickets2){
        Optional<tickets> ticketsOptional=ticketsRepository.findByEmployeeId(tickets2.getEmployee_id());
        return ticketsOptional.orElse(null);

    }




}
