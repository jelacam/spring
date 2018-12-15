package com.example.codingchallenge.controller;

import com.example.codingchallenge.model.Operation;
import com.example.codingchallenge.model.Organization;
import com.example.codingchallenge.model.ProductSharingStatement;
import com.example.codingchallenge.securityconfig.CustomPrincipal;
import com.example.codingchallenge.service.OrganizationService;
import com.example.codingchallenge.service.ProductSharingService;
import com.sun.org.apache.xerces.internal.util.HTTPInputSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.http.HTTPBinding;
import java.util.List;

@RestController
@RequestMapping("/productSharing")
public class ProductSharingController {

    @Autowired
    private ProductSharingService productSharingService;

    @Autowired
    private OrganizationService organizationService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void CreateProductSharingStatement(Authentication authentication, ProductSharingStatement productSharingStatement){
        String sharingOrgId = ((CustomPrincipal) authentication.getPrincipal()).getUser().getOrganizationId();
        productSharingStatement.setSharingOrgId(sharingOrgId);
        productSharingStatement.setApproved(false);
        productSharingService.CreateProductSharingStatement(productSharingStatement);
    }

    @RequestMapping(value = "/approve", method = RequestMethod.PUT)
    public HttpStatus ApproveProductSharingStatement(Authentication authentication, String id){
        if (IsOrganizationMaster(authentication)) {
            productSharingService.ApproveSharingStatement(id);
            return HttpStatus.ACCEPTED;
        }
        return HttpStatus.FORBIDDEN;
    }

    @RequestMapping(value = "/forbid", method = RequestMethod.PUT)
    public HttpStatus ForbidProductSharingStatement(Authentication authentication, String id){
        if (IsOrganizationMaster(authentication)) {
            productSharingService.ForbidSharingStatement(id);
            return HttpStatus.ACCEPTED;
        }
        return HttpStatus.FORBIDDEN;
    }

    @RequestMapping(value = "/statement", method = RequestMethod.GET)
    public List<ProductSharingStatement> FindByAccessingOrgIdAndOperation(String accessingOrgId, String operation){
        return productSharingService.FindProductSharingByAccessingOrgIdAndOperation(accessingOrgId, Operation.valueOf(operation));
    }

    private boolean IsOrganizationMaster(Authentication authentication){
        CustomPrincipal principal = (CustomPrincipal) authentication.getPrincipal();
        String organizationId = principal.getUser().getOrganizationId();
        Organization organization = organizationService.OrganizationByID(organizationId);
        return organization.getMaster();
    }
}
