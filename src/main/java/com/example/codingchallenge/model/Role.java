package com.example.codingchallenge.model;

import java.util.List;

public class Role {

    private String id; //nn pf
    private String name;
    private String organizationId; // nn fk
    private List<Permission> permissions;


    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Permission> getPermissions() {
        return permissions;
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
