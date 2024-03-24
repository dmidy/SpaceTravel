package org.example.dao;

import org.example.model.Planet;

import java.util.List;

public interface PlanetDAO {
    void savePlanet(Planet planet) throws Exception;
    Planet getPlanet(String id) throws Exception;
    List<Planet> getAllPlanets() throws Exception;
    void updatePlanet(String id, String name) throws Exception;
    void deletePlanet(String id) throws Exception;
}
