package com.doublev.tickets.controllers;

import com.doublev.tickets.dao.TicketsDao;
import com.doublev.tickets.models.Ticket;
import com.doublev.tickets.util.TicketsHttpMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TicketsController {

    @Autowired
    private TicketsDao ticketsDao;

    @RequestMapping(value = "tickets", method = RequestMethod.GET)
    public List<Ticket> getTickets(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){

        Pageable pageable = PageRequest.of(page, size);

        return ticketsDao.getTickets(pageable);
    }
    @RequestMapping(value = "ticket/{id}", method = RequestMethod.GET)
    public Ticket getTicketById(@PathVariable long id){


        return ticketsDao.getTicketById(id);
    }

    @RequestMapping(value = "postTicket", method = RequestMethod.POST)
    ResponseEntity<TicketsHttpMessage> postTicket(@RequestBody Ticket ticket){

        return ticketsDao.postTicket(ticket);
    }

    @RequestMapping(value = "updateTicket/{id}", method = RequestMethod.PUT)
    ResponseEntity<TicketsHttpMessage> updateTicket(@PathVariable long id, @RequestBody Ticket ticket){
        return ticketsDao.updateTicket(id, ticket);
    }

    @RequestMapping(value = "deleteTicket/{id}", method = RequestMethod.DELETE)
    ResponseEntity<TicketsHttpMessage> deleteTicket(@PathVariable long id){
        return ticketsDao.deleteTicket(id);
    }


}
