package org.example.dao;

import org.example.model.Client;
import org.example.model.Ticket;

import java.util.List;

public interface ClientDAO {
    void saveClient(Client client) throws Exception;
    Client getClient(int id) throws Exception;
    List<Client> getAllClients() throws Exception;
    List<Ticket> getAllTickets(Long clientId) throws Exception;
    void updateClient(Long id, String name) throws Exception;
    void deleteClient(Long clientId) throws Exception;
}