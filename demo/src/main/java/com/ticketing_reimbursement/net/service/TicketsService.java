package com.ticketing_reimbursement.net.service;


import com.ticketing_reimbursement.net.entity.Employee;
import com.ticketing_reimbursement.net.entity.tickets;
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
    public tickets createTickets(tickets tickets1){
        if(tickets1.getDescription().length()<1)
            return null;

        if(tickets1.getDescription().length()>255)
            return null;


        return ticketsRepository.save(tickets1);
    }

    public List<tickets> showPendingTickets(){
        Optional<List<tickets>> ticketsOptional=ticketsRepository.findAllByStatus("pending");
        return ticketsOptional.orElse(null);

    }
    public List<tickets> showEmployeeTickets(Employee employee){
        Optional<List<tickets>> ticketsOptional=ticketsRepository.findAllByEmployeeID(employee.getId());
        return ticketsOptional.orElse(null);
    }
    public int UpdatedticketById(Long ticketsID, tickets tickets1){

        if(tickets1.getStatus().length()<1)
            return 0;

        Optional<tickets> ticketsOptional =ticketsRepository.findById(ticketsID);

        if(ticketsOptional.isPresent()){
            tickets newTickets=ticketsOptional.get();
            newTickets.setStatus(tickets1.getStatus());
           ticketsRepository.save(newTickets);
            return 1;
        }

        return 0;


    }
}