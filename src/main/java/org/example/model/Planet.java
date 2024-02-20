package org.example.model;

import jakarta.persistence.*;

@Entity
public class Planet {
    @Id
    private String id;
    @Column(name = "Name", length = 500)
    private String name;
    public Planet(){
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
}
