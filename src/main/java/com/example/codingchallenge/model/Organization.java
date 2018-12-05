package com.example.codingchallenge.model;

import java.io.Serializable;

public class Organization implements Serializable {

    private String id; // nn PK
    private String name; // nn unique
    private Boolean master;

    public Organization(String name, Boolean master) {
        this.name = name;
        this.master = master;
    }

    public Organization(){

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean getMaster() {
        return master;
    }

    public void setId(String id) { this.id = id; }

    public void setName(String name) {
        this.name = name;
    }

    public void setMaster(Boolean master) {
        this.master = master;
    }

}
