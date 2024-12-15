package com.ticketing_reimbursement.net.repositories;


import com.ticketing_reimbursement.net.entity.tickets;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface  TicketRepository extends CrudRepository<tickets, Long> {


    public Optional<List<tickets>>findAllByEmployeeID(Long employeeID);
    public Optional<List<tickets>>findAllByStatus(String status);



}