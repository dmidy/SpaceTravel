package org.example.crud;

import org.example.dao.TicketDAO;
import org.example.model.Client;
import org.example.model.Planet;
import org.example.model.Ticket;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.List;


public class TicketDAOImpl implements TicketDAO {
    private SessionFactory sessionFactory = HibernateUtil.getInstance().getSessionFactory();

    @Override
    public void saveTicket(Ticket ticket) throws Exception {
        if (ticket.getClient() == null || ticket.getFromPlanet() == null || ticket.getToPlanet() == null) {
            throw new Exception("Cannot save a ticket with null client or planet");
        }
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            if (!existsById(Client.class, ticket.getClient().getId()) ||
                    !existsById(Planet.class, ticket.getFromPlanet().getId()) ||
                    !existsById(Planet.class, ticket.getToPlanet().getId())) {
                throw new Exception("Client or planet does not exist");
            }
            transaction = session.beginTransaction();
            session.persist(ticket);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new Exception("Failed to save ticket", e);
        }
    }

    private boolean existsById(Class<?> entityClass, Serializable id) {
        try (Session session = sessionFactory.openSession()) {
            Object loadedEntity = session.get(entityClass, id);
            return loadedEntity != null;
        }
    }

    @Override
    public Ticket getTicket(int id) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Ticket.class, id);
        } catch (Exception e) {
            throw new Exception("Failed to fetch the ticket");
        }
    }

    @Override
    public List<Ticket> getAllTickets() throws Exception {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Ticket", Ticket.class).list();
        } catch (Exception e) {
            throw new Exception("Failed to fetch the ticket list");
        }
    }

    @Override
    public void updateTicket(Ticket newUpdatedTicket) throws Exception {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Ticket ticket = session.get(Ticket.class, newUpdatedTicket.getId());
            if (ticket != null) {
                ticket.setClient(newUpdatedTicket.getClient());
                ticket.setCreatedAt(newUpdatedTicket.getCreatedAt());
                ticket.setFromPlanet(newUpdatedTicket.getFromPlanet());
                ticket.setToPlanet(newUpdatedTicket.getToPlanet());
            } else {
                throw new Exception("Ticket not found with id:" + newUpdatedTicket.getId());
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new Exception("Failed to update the ticket");
        }
    }

    @Override
    public void deleteTicket(int id) throws Exception {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Ticket ticket = session.get(Ticket.class, id);
            if (ticket != null) {
                session.delete(ticket);
            } else {
                throw new Exception("Ticket not found with id:" + id);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new Exception("Failed to delete the ticket");
        }
    }
}