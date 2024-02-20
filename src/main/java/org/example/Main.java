package org.example;

import org.example.dao.ClientDao;
import org.example.dao.PlanetDao;
import org.example.model.Client;
import org.example.model.Planet;
import org.example.util.HibernateUtil;
import org.example.util.MigrationUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        MigrationUtil migrationUtil = new MigrationUtil();
        migrationUtil.migrate();

        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            ClientDao clientDao = new ClientDao();
            PlanetDao planetDao = new PlanetDao();

            Transaction transaction = session.beginTransaction();

            Client client = new Client();
            client.setName("Lev Grib");
            clientDao.save(client);
            Query queryClient = session.createNativeQuery("SELECT name FROM client");
            List<String> clientNames = queryClient.list();

            Planet planet = new Planet();
            planet.setId("PLU");
            planet.setName("Pluto");
            planetDao.save(planet);
            Query queryPlanet = session.createNativeQuery("SELECT name FROM planet");
            List<String> planetsNames = queryPlanet.list();

            transaction.commit();

            System.out.println("Clients list:");
            for (String name : clientNames) {
                System.out.println(name);
            }

            System.out.println("\nPlanet list:");
            for (String name : planetsNames) {
                System.out.println(name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}