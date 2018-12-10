package com.example.codingchallenge.model;

public class Product {

    private String id; // nn pf
    private String name; //nn
    private String  description;
    private Double price;
    private String organizationId; // nn fk
    private int quantity;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public int getQuantity() { return quantity; }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public void setQuantity(int quantity) { this.quantity = quantity; }


}
