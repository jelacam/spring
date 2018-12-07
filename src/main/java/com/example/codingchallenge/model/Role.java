package com.example.codingchallenge.model;

public class Role {

    private String id; //nn pf
    private String name;
    private String organizationId; // nn fk

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }





    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOrganizationId() {
        return organizationId;
    }




}
