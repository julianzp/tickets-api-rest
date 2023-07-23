package com.doublev.tickets.dao;

import com.doublev.tickets.models.Ticket;
import com.doublev.tickets.util.TicketsHttpMessage;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TicketsDao {

    List<Ticket> getTickets(Pageable pageable);

    Ticket getTicketById(long id);

    ResponseEntity<TicketsHttpMessage> postTicket(Ticket ticket);

    ResponseEntity<TicketsHttpMessage> updateTicket(long id, Ticket ticket);

    ResponseEntity<TicketsHttpMessage> deleteTicket(long id);


}

