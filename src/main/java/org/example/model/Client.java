package org.example.model;

import jakarta.persistence.*;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "Name", length = 200)
    private String name;
    public Client(){
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}

