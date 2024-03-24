package org.example.dao;

import org.example.model.Ticket;

import java.util.List;

public interface TicketDAO {
    void saveTicket(Ticket ticket) throws Exception;
    Ticket getTicket(int id) throws Exception;
    List<Ticket> getAllTickets() throws Exception;
    void updateTicket(Ticket newUpdatedTicket) throws Exception;
    void deleteTicket(int id) throws Exception;
}
