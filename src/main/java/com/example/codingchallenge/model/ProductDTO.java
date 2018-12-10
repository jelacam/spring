package com.example.codingchallenge.model;

public class ProductDTO {

    private Operation[] allowedOps;
    private Product product;

    public Operation[] getAllowedOps() {
        return allowedOps;
    }

    public Product getProduct() {
        return product;
    }

    public void setAllowedOps(Operation[] allowedOps) {
        this.allowedOps = allowedOps;
    }

    public void setProduct(Product product) {
        this.product = product;
    }





}
