package com.example.codingchallenge.model;

import java.util.List;

public class AdminRole {

    private String id; // nn pk
    private String adminId; //nn fk
    private String roleId; // nn fk
    private List<Role> roles;

    public List<Role> getRoles() {
        return roles;
    }

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

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
