package com.example.codingchallenge.controller;

import com.example.codingchallenge.model.Product;
import com.example.codingchallenge.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void CreateProduct(@ModelAttribute Product product) {
        productService.CreateProduct(product);
    }

    @PreAuthorize("hasPermission(new com.example.codingchallenge.model.Product(), 'READ')")
    @RequestMapping(value = "/getall", method = RequestMethod.GET)
    List<Product> GetAllProducts(){
        return productService.GetAllProducts();
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    boolean UpdateProduct(Product product) {
        return productService.UpdateProduct(product);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    boolean DeleteProduct(@PathVariable  String id){
        return productService.DeleteProduct(id);
    }

}
