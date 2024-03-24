package org.example.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "planet")
public class Planet {
    @Id
    @Column(name = "ID")
    private String id;
    @Column(name = "Name", length = 500)
    private String name;

    @OneToMany(mappedBy = "fromPlanet")
    private Set<Ticket> ticketsFrom;

    @OneToMany(mappedBy = "toPlanet")
    private Set<Ticket> ticketsTo;

    public Planet() {
    }
    public Planet(String id, String name) {
        this.id = id;
        this.name = name;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Planet{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
