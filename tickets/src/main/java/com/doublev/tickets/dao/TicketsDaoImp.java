package com.doublev.tickets.dao;

import com.doublev.tickets.models.Ticket;
import com.doublev.tickets.util.TicketsHttpMessage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

@Repository
@Transactional
public class TicketsDaoImp implements TicketsDao {

    public static final String ESTATUS_ABIERTO = "abierto";
    public static final String ESTATUS_CERRADO = "cerrado";

    private TicketsHttpMessage ticketsMessage = new TicketsHttpMessage();


    @PersistenceContext
    private EntityManager entityManager;

    /********************************* REST METHODS ********************************************************************
     */
    @Override
    public List<Ticket> getTickets(Pageable pageable) {

        String query = "FROM Ticket";
        TypedQuery<Ticket> typedQuery = entityManager.createQuery(query, Ticket.class);

        // Traer lista paginada
        typedQuery.setFirstResult((int) pageable.getOffset());
        typedQuery.setMaxResults(pageable.getPageSize());

        List<Ticket> resultList = typedQuery.getResultList();

        // Validar si la lista está vacías
        if (resultList.isEmpty()) {
            return Collections.emptyList();
        }

        return resultList;
    }

    @Override
    public Ticket getTicketById(long id) {
        Ticket ticket = entityManager.find(Ticket.class, id);

        if (ticket == null) {
            throw new EntityNotFoundException("Ticket with  " + id + " not found. Check the ID");
        }

        return ticket;
    }

    @Override
     public ResponseEntity<TicketsHttpMessage> postTicket(Ticket ticket) {

        ResponseEntity<TicketsHttpMessage> BAD_REQUEST = validarEstatus(ticket);
        if (BAD_REQUEST != null) return BAD_REQUEST;

        ticket.setFechaCreacion(LocalDate.now());

        entityManager.merge(ticket);

        ticketsMessage = setResponse(ticketsMessage, 200, "Record Saved Successfully");

        return new ResponseEntity<>(ticketsMessage, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TicketsHttpMessage> updateTicket(long id, Ticket ticket) {

        ResponseEntity<TicketsHttpMessage> BAD_REQUEST = validarEstatus(ticket);
        if (BAD_REQUEST != null) return BAD_REQUEST;

        Ticket ticketUpdate = entityManager.find(Ticket.class, id);

        ticketUpdate.setUsuario(ticket.getUsuario());
        ticketUpdate.setFechaCreacion(ticketUpdate.getFechaCreacion());
        ticketUpdate.setFechaActualizacion(LocalDate.now());
        ticketUpdate.setEstatus(ticket.getEstatus());

        entityManager.persist(ticketUpdate);

        ticketsMessage = setResponse(ticketsMessage, 200, "Record Updated Successfully");

        return new ResponseEntity<>(ticketsMessage, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<TicketsHttpMessage> deleteTicket(long id) {
        Ticket ticket = entityManager.find(Ticket.class, id);
        entityManager.remove(ticket);
        ticketsMessage = setResponse(ticketsMessage, 200, "Record Deleted");
        return new ResponseEntity<>(ticketsMessage, HttpStatus.OK);
    }

    /*******************************************************************************************************************
     */

    /*
        This method validated if the estatus field is written correctly
     */
    private static ResponseEntity<TicketsHttpMessage> validarEstatus(Ticket ticket) {
        TicketsHttpMessage ticketsMessage = new TicketsHttpMessage();
        if(!ticket.getEstatus().equalsIgnoreCase(ESTATUS_ABIERTO) && !ticket.getEstatus().equalsIgnoreCase(ESTATUS_CERRADO)){

            ticketsMessage = setResponse(ticketsMessage, 400, "Estatus Field Must Be: abierto/cerrado");
            return new ResponseEntity<>(ticketsMessage, HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    /*
    This method set the response when sending a request
    */
    private static TicketsHttpMessage setResponse(TicketsHttpMessage httpResponse, int status, String message) {

        OffsetDateTime now = OffsetDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        String fechaFormateada = now.format(formatter);

        httpResponse.setTimestamp(fechaFormateada);
        httpResponse.setStatus(status);
        httpResponse.setMessage(message);

        return httpResponse;
    }

}
