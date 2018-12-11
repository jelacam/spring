package com.example.codingchallenge.service;

import com.example.codingchallenge.model.Product;

import java.util.List;

public interface ProductService {
    void CreateProduct(Product product);
    List<Product> GetAllProducts(String accessingOrgId);
    boolean UpdateProduct(Product product);
    boolean DeleteProduct(String id);
    Product FindById(String id);
}
