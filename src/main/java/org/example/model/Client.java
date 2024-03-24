package org.example.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "Name", length = 200)
    private String name;

    @OneToMany(mappedBy = "client")
    private Set<Ticket> tickets;
    public Client() {
    }
    public Client(String name){
        this.name = name;
    }
    public void setId(int id) {
        this.id = id;
    }
    public long getId() {
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
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

