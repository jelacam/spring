package com.example.codingchallenge.model;


public class ProductSharingStatement {

    private String id; //nn PK
    private String sharingOrgId; //nn FK
    private String accessingOrgId; //nn FK
    private int quantity;
    private float price;
    private Boolean approved;
    private Relation relation;
    private Operation operation;

    public String getId() {
        return id;
    }

    public String getSharingOrgId() {
        return sharingOrgId;
    }

    public String getAccessingOrgId() {
        return accessingOrgId;
    }

    public int getQuantity() {
        return quantity;
    }

    public Boolean getApproved() {
        return approved;
    }

    public Relation getRelation() {
        return relation;
    }

    public Operation getOperation() {
        return operation;
    }

    public float getPrice() {return price; }

    public void setId(String id) {
        this.id = id;
    }

    public void setSharingOrgId(String sharingOrgId) {
        this.sharingOrgId = sharingOrgId;
    }

    public void setAccessingOrgId(String accessingOrgId) {
        this.accessingOrgId = accessingOrgId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public void setRelation(Relation relation) {
        this.relation = relation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public void setPrice(float price) { this.price = price; }

}
