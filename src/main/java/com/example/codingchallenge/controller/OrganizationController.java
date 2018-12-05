package com.example.codingchallenge.controller;

import com.example.codingchallenge.model.Organization;
import com.example.codingchallenge.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/organization")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @RequestMapping(value = "/create", method =  RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void createOrganization(@ModelAttribute Organization organization ){
        organizationService.CreateOrganization(organization);
        System.out.println(organization);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public List<Organization> getOrganizations(){
        return organizationService.getAll();
    }


}
