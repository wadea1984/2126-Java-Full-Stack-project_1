package com.ticketing_reimbursement.net.service;


import com.ticketing_reimbursement.net.entity.Ticket;
import com.ticketing_reimbursement.net.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketsService {
    @Autowired
    TicketRepository ticketsRepository;

    public TicketsService(TicketRepository ticketsRepository) {
        this.ticketsRepository = ticketsRepository;
    }
    public Ticket createTickets(Ticket ticket1){
        if(ticket1.getDescription().length()<1)
            return null;

        if(ticket1.getDescription().length()>255)
            return null;
        if(ticket1.getAmount()<2)
            return null;


        return ticketsRepository.save(ticket1);
    }

    public List<Ticket> showPendingTickets(){
        Optional<List<Ticket>> ticketsOptional=ticketsRepository.findAllByStatus("pending");
        return ticketsOptional.orElse(null);

    }
    public List<Ticket> showEmployeeTickets(Long employeeID){
        Optional<List<Ticket>> ticketsOptional=ticketsRepository.findAllByEmployeeID(employeeID);
        return ticketsOptional.orElse(null);
    }
    public int UpdatedticketById(Long ticketsID, Ticket ticket1){

        if(ticket1.getStatus().length()<1)
            return 0;

        Optional<Ticket> ticketsOptional =ticketsRepository.findById(ticketsID);

        if(ticketsOptional.isPresent()){
            Ticket newTicket =ticketsOptional.get();
            newTicket.setStatus(ticket1.getStatus());
           ticketsRepository.save(newTicket);
            return 1;
        }

        return 0;


    }
}