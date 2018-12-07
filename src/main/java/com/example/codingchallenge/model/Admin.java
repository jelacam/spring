package com.example.codingchallenge.model;

public class Admin {

    private String id; //PK
    private String firstName; //NN
    private String lastName; //NN
    private String email; //NN
    private String password;

    public Admin(){

    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getOrganizationId() {
        return organizationId;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    private String organizationId; //NN FK

}
