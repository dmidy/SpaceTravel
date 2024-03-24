package org.example.crud;

import jakarta.transaction.Transaction;
import org.example.dao.ClientDAO;
import org.example.model.Client;
import org.example.model.Ticket;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ClientDAOImpl implements ClientDAO{
    private SessionFactory sessionFactory = HibernateUtil.getInstance().getSessionFactory();


    @Override
    public void saveClient(Client client) throws Exception {
        org.hibernate.Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(client);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new Exception("Failed to save the client");
        }
    }

    @Override
    public Client getClient(int id) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            Client client = session.get(Client.class, id);
            if (client == null) {
                throw new Exception("Client not found with ID: " + id);
            }
            return client;
        } catch (Exception e) {
            throw new Exception("Failed to fetch the client with ID: " + id, e);
        }
    }

    @Override
    public List<Client> getAllClients() throws Exception {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Client", Client.class).list();
        } catch (Exception e) {
            throw new Exception("Failed to fetch all clients");
        }
    }

    @Override
    public List<Ticket> getAllTickets(Long clientId) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Ticket", Ticket.class).list();
        } catch (Exception e) {
            throw new Exception("Failed to fetch the ticket list");
        }
    }

    @Override
    public void updateClient(Long id, String name) throws Exception {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = (Transaction) session.beginTransaction();
            Client client = session.get(Client.class, id);
            if (client != null) {
                client.setName(name);
            } else {
                throw new Exception("Client not found with ID: " + id);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new Exception("Failed to update the client with ID: " + id);
        }
    }

    @Override
    public void deleteClient(Long clientId) throws Exception {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = (Transaction) session.beginTransaction();
            Client client = session.get(Client.class, clientId);
            if (client != null) {
                session.delete(client);
            } else {
                throw new Exception("Client not found with ID: " + clientId);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new Exception("Failed to delete the client with ID: " + clientId);
        }
    }
}