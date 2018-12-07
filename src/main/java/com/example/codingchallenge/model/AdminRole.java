package com.example.codingchallenge.model;

public class AdminRole {

    private String id; // nn pk
    private String adminId; //nn fk
    private String roleId; // nn fk

    public String getId() {
        return id;
    }

    public String getAdminId() {
        return adminId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }


}
