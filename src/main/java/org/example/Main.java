package org.example;

import org.example.crud.ClientDAOImpl;
import org.example.crud.PlanetDAOImpl;
import org.example.crud.TicketDAOImpl;
import org.example.model.Client;
import org.example.model.Planet;
import org.example.model.Ticket;
import org.example.util.MigrationUtil;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        MigrationUtil migrationUtil = new MigrationUtil();
        migrationUtil.migrate();

        ClientDAOImpl clientDAO = new ClientDAOImpl();
        PlanetDAOImpl planetDAO = new PlanetDAOImpl();
        TicketDAOImpl ticketDAO = new TicketDAOImpl();

        // Создание клиента
        Client client = new Client("Lev Grib");
        clientDAO.saveClient(client);

        // Создание планеты
        Planet planet = new Planet("ALC","Alfa Centaur");
        planetDAO.savePlanet(planet);

        //Создание тикета
        ticketDAO.saveTicket(new Ticket(clientDAO.getClient(10), planetDAO.getPlanet("ALC"), planetDAO.getPlanet("VEN")));

        List<Ticket> allTickets = ticketDAO.getAllTickets();

        System.out.println(allTickets);
    }
}