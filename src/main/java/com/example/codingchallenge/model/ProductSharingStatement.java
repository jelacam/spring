package com.example.codingchallenge.model;


public class ProductSharingStatement {
    private String id; //nn PK
    private String sharingOrgId; //nn FK
    private String accessingOrgId; //nn FK
    private int quantity;
    private Boolean approved;
    private Relation relation;
}
