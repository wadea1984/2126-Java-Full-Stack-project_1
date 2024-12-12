package com.ticketing_reimbursement.net.repositories;

import com.ticketing_reimbursement.net.entity.tickets;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface  TicketRepository extends JpaRepository<tickets, Long> {


}