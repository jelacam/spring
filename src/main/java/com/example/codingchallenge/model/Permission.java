package com.example.codingchallenge.model;

public class Permission {

    private String id; //nn fk
    private Entity entity; //nn
    private Operation operation; //nn
    private String roleId; // nn fk

    public String getId() {
        return id;
    }

    public Entity getEntity() {
        return entity;
    }

    public Operation getOperation() {
        return operation;
    }

    public String getRoleId() {
        return roleId;
    }



    public void setId(String id) {
        this.id = id;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

}
