package com.ticketing_reimbursement.net.repositories;


import com.ticketing_reimbursement.net.entity.tickets;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface  TicketRepository extends CrudRepository<tickets, Long> {

    public Optional<tickets> findByEmployeeId(Long employeeID);
    public Optional<tickets> findByStatus(String status);


}