package com.example.codingchallenge.controller;

import com.example.codingchallenge.model.Operation;
import com.example.codingchallenge.model.ProductSharingStatement;
import com.example.codingchallenge.service.ProductSharingService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/productSharing")
public class ProductSharingController {

    @Autowired
    private ProductSharingService productSharingService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void CreateProductSharingStatement(ProductSharingStatement productSharingStatement){
        productSharingService.CreateProductSharingStatement(productSharingStatement);
    }

    @RequestMapping(value = "/approve", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void ApproveProductSharingStatement(Authentication authentication, String id){

        productSharingService.ApproveSharingStatement(id);
    }

    @RequestMapping(value = "/forbid", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void ForbidProductSharingStatement(String id){
        productSharingService.ForbidSharingStatement(id);
    }

    @RequestMapping(value = "/statement", method = RequestMethod.GET)
    public List<ProductSharingStatement> FindByAccessingOrgIdAndOperation(String accessingOrgId, String operation){
        return productSharingService.FindProductSharingByAccessingOrgIdAndOperation(accessingOrgId, Operation.valueOf(operation));
    }


}
