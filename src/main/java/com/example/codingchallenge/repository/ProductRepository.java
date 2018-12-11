package com.example.codingchallenge.repository;

import com.example.codingchallenge.model.Product;
import com.example.codingchallenge.model.SharingStatementQuery;

import java.util.List;

public interface ProductRepository {
    void CreateProduct(Product product);
    List<Product> GetAllProducts();
    boolean UpdateProduct(Product product);
    boolean DeleteProduct(String id);
    Product FindById(String id);
    List<Product> GetAllProducts(List<SharingStatementQuery> sharingStatementQueries, String accessingOrgId);

}
