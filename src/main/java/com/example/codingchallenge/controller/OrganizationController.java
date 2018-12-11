package com.example.codingchallenge.controller;

import com.example.codingchallenge.model.Organization;
import com.example.codingchallenge.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/organization")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @PreAuthorize("hasPermission(#organization.id, 'ORGANIZATION', 'CREATE')")
    @RequestMapping(value = "/create", method =  RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void CreateOrganization(@ModelAttribute Organization organization ){
        String id = UUID.randomUUID().toString();
        organization.setId(id);
        organizationService.CreateOrganization(organization);
    }


    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Organization> GetOrganizations(){
        return organizationService.getAll();
    }

    @PreAuthorize("hasPermission(#organization.id, 'ORGANIZATION', 'UPDATE')")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public boolean UpdateOrganization(@ModelAttribute Organization organization){
        return organizationService.UpdateOrganization(organization);
    }

    @PreAuthorize("hasPermission(#id, 'ORGANIZATION', 'DELETE')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public boolean DeleteOrganization(@PathVariable String id){
        return organizationService.DeleteOrganization(id);
    }
}
