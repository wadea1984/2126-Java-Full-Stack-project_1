package com.ticketing_reimbursement.net.repositories;


import com.ticketing_reimbursement.net.entity.Ticket;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface  TicketRepository extends CrudRepository<Ticket, Long> {


    public Optional<List<Ticket>>findAllByEmployeeID(Long employeeID);
    public Optional<List<Ticket>>findAllByStatus(String status);



}