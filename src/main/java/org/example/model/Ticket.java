package org.example.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "CREATED_AT")
    private Timestamp createdAt;

    @ManyToOne
    @JoinColumn(name = "CLIENT_ID", nullable=false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "FROM_PLANET_ID", nullable=false)
    private Planet fromPlanet;

    @ManyToOne
    @JoinColumn(name = "TO_PLANET_ID", nullable=false)
    private Planet toPlanet;

    public Ticket() {
    }
    public Ticket(Client client, Planet fromPlanet, Planet toPlanet) {
        this.client = client;
        this.toPlanet = toPlanet;
        this.fromPlanet = fromPlanet;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    public Planet getFromPlanet() {
        return fromPlanet;
    }
    public void setFromPlanet(Planet fromPlanet) {
        this.fromPlanet = fromPlanet;
    }
    public Planet getToPlanet() {
        return toPlanet;
    }
    public void setToPlanet(Planet toPlanet) {
        this.toPlanet = toPlanet;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", client=" + client.getName() +
                ", fromPlanet=" + fromPlanet.getName() +
                ", toPlanet=" + toPlanet.getName() +
                '}';
    }
}