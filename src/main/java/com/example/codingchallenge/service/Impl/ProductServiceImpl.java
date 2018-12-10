package com.example.codingchallenge.service.Impl;

import com.example.codingchallenge.model.Product;
import com.example.codingchallenge.repository.ProductRepository;
import com.example.codingchallenge.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void CreateProduct(Product product) {
        String id = UUID.randomUUID().toString();
        product.setId(id);
        productRepository.CreateProduct(product);
    }

    @Override
    public List<Product> GetAllProducts() {
        return productRepository.GetAllProducts();
    }

    @Override
    public boolean UpdateProduct(Product product) {
        return productRepository.UpdateProduct(product);
    }

    @Override
    public boolean DeleteProduct(String id) {
        return productRepository.DeleteProduct(id);
    }

    @Override
    public Product FindById(String id) {
        return productRepository.FindById(id);
    }
}
