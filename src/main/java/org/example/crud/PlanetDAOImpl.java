package org.example.crud;

import org.example.dao.PlanetDAO;
import org.example.model.Planet;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class PlanetDAOImpl implements PlanetDAO {
    private SessionFactory sessionFactory = HibernateUtil.getInstance().getSessionFactory();

    @Override
    public void savePlanet(Planet planet) throws Exception {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(planet);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new Exception("Failed to save the planet");
        }
    }

    @Override
    public Planet getPlanet(String id) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Planet.class, id);
        } catch (NumberFormatException e) {
            throw new Exception("Invalid planet ID format: " + id);
        } catch (Exception e) {
            throw new Exception("Failed to fetch the planet");
        }
    }

    public List<Planet> getAllPlanets() {
        try (Session session = sessionFactory.openSession()) {
            Query<Planet> query = session.createQuery("FROM Planet", Planet.class);
            return query.list();
        }
    }

    @Override
    public void updatePlanet(String id, String name) throws Exception {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Planet planet = session.get(Planet.class, id);
            if (planet != null) {
                planet.setName(name);
            } else {
                throw new Exception("Planet not found with id: " + id);
            }
            transaction.commit();
        } catch (NumberFormatException e) {
            throw new Exception("Invalid planet ID format: " + id);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new Exception("Failed to update the planet");
        }
    }

    @Override
    public void deletePlanet(String id) throws Exception {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();

            Planet planet = session.get(Planet.class, id);
            if (planet != null) {
                session.delete(planet);
                transaction.commit();
            } else {
                throw new Exception("Planet not found with id: " + id);
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new Exception("Failed to delete the planet");
        }finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
