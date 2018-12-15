package com.example.codingchallenge.controller;

import com.example.codingchallenge.model.*;
import com.example.codingchallenge.securityconfig.CustomPrincipal;
import com.example.codingchallenge.service.Impl.ProductSharingServiceImpl;
import com.example.codingchallenge.service.ProductService;
import com.example.codingchallenge.service.ProductSharingService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ProductSharingService productSharingService;

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
        String accessingOrgId = ((CustomPrincipal) userDetails).getUser().getOrganizationId();
        List<Product> products = productService.GetAllProducts(accessingOrgId);

        return CreateProductDTOS(accessingOrgId, products, userDetails);
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
    public ProductDTO ProductById(Authentication authentication, @PathVariable String id){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String accessingOrgId = ((CustomPrincipal) userDetails).getUser().getOrganizationId();
        Product product = productService.FindById(id);
        List<Product> products = new ArrayList<>();
        products.add(product);
        return CreateProductDTOS(accessingOrgId, products, userDetails).get(0);
    }

    private List<Operation> GetUserAllowedOperations(UserDetails userDetails) {
        List<Operation> operationList = new ArrayList<>();
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        for (GrantedAuthority authority : authorities){
            if (authority.getAuthority().startsWith(Entity.PRODUCT.toString()) &&
                                                    !authority.getAuthority().contains(Operation.CREATE.toString())){
                String operation = authority.getAuthority().split("_")[1];
                operationList.add(Operation.valueOf(operation.toUpperCase()));
            }
        }

        return operationList;
    }


    private List<ProductDTO> CreateProductDTOS(String accessingOrgId, List<Product> products, UserDetails userDetails){

        List<Operation> allowedUserOps = GetUserAllowedOperations(userDetails);

        List<ProductSharingStatement> productSharingStatements = productSharingService
                .FindProductSharingByAccessingOrgIdAndOperation(accessingOrgId, null);
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (Product product: products) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setProduct(product);
            if (product.getOrganizationId().equals(accessingOrgId)){
                productDTO.setAllowedOps(ToArray(allowedUserOps));
            }
            else {
                List<Operation> sharedOperations = new ArrayList<>();
                for (ProductSharingStatement productSharingStatement : productSharingStatements) {
                    if (productSharingStatement.getSharingOrgId().equals(product.getOrganizationId())) {
                        Operation sharingOperation = productSharingStatement.getOperation();

                        boolean priceRelation = true;
                        boolean quantityRelation = true;

                        if (productSharingStatement.getRelation() != null) {
                            if (productSharingStatement.getPrice() > 0) {
                                priceRelation = CheckRelationStatement(product.getPrice(), productSharingStatement.getRelation(), productSharingStatement.getPrice());
                            }
                            if (productSharingStatement.getQuantity() > 0) {
                                quantityRelation = CheckRelationStatement(product.getQuantity(), productSharingStatement.getRelation(), productSharingStatement.getQuantity());
                            }

                        }
                        if (quantityRelation && priceRelation) {
                            // add operation if user has that permission
                            if (allowedUserOps.contains(sharingOperation)) {
                                sharedOperations.add(sharingOperation);
                            }
                        }
                    }
                }
                productDTO.setAllowedOps(ToArray(sharedOperations));
            }
            productDTOS.add(productDTO);
        }
        return productDTOS;
    }

    private boolean CheckRelationStatement(double attribute, Relation relation, double value) {
        switch (relation) {
            case EQ: {
                return attribute == value;
            }
            case GT: {
                return attribute > value;
            }
            case GTE: {
                return attribute >= value;
            }
            case LT: {
                return attribute < value;
            }
            case LTE: {
                return attribute <= value;
            }
            default: {
                return  false;
            }
        }
    }

    private Operation[] ToArray(List<Operation> operationList) {
        Operation[] operations = new Operation[operationList.size()];
        operations = operationList.toArray(operations);
        return operations;
    }

}
