package com.example.codingchallenge.controller;

import com.example.codingchallenge.model.Entity;
import com.example.codingchallenge.model.Operation;
import com.example.codingchallenge.model.Product;
import com.example.codingchallenge.model.ProductDTO;
import com.example.codingchallenge.securityconfig.CustomPrincipal;
import com.example.codingchallenge.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PreAuthorize("hasPermission(new com.example.codingchallenge.model.Product(), 'CREATE')")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void CreateProduct(Authentication authentication, @ModelAttribute Product product) {
        String orgId = ((CustomPrincipal) authentication.getPrincipal()).getUser().getOrganizationId();
        product.setOrganizationId(orgId);
        productService.CreateProduct(product);
    }

    @PreAuthorize("hasPermission(new com.example.codingchallenge.model.Product(), 'READ')")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<ProductDTO> GetAllProducts(Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Operation[] allowedOps = GetUserAllowedOperations(userDetails);

        List<Product> products = productService.GetAllProducts(((CustomPrincipal) userDetails).getUser().getOrganizationId());
        List<ProductDTO> productDTOS = new ArrayList<>();

        for (Product product : products){
            ProductDTO productDTO = new ProductDTO();
            productDTO.setProduct(product);
            productDTO.setAllowedOps(allowedOps);
            productDTOS.add(productDTO);
        }

        return  productDTOS;
    }

    @PreAuthorize("hasPermission(#product.id, 'PRODUCT', 'UPDATE')")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public boolean UpdateProduct(@ModelAttribute Product product) {
        return productService.UpdateProduct(product);
    }

    @PreAuthorize("hasPermission(#id, 'PRODUCT', 'DELETE')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    boolean DeleteProduct(@PathVariable  String id){
        return productService.DeleteProduct(id);
    }

    @PreAuthorize("hasPermission(#id, 'PRODUCT', 'READ')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Product ProductById(@PathVariable String id){
        return productService.FindById(id);
    }

    private Operation[] GetUserAllowedOperations(UserDetails userDetails) {
        List<Operation> operationList = new ArrayList<>();
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        for (GrantedAuthority authority : authorities){
            if (authority.getAuthority().startsWith(Entity.PRODUCT.toString())){
                String operation = authority.getAuthority().split("_")[1];
                operationList.add(Operation.valueOf(operation.toUpperCase()));
            }
        }
        Operation[] operations = new Operation[operationList.size()];
        operations = operationList.toArray(operations);
        return operations;
    }
}
