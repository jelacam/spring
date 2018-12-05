package com.example.codingchallenge.service.Impl;

import com.example.codingchallenge.model.Organization;
import com.example.codingchallenge.repository.OrganizationRepository;
import com.example.codingchallenge.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationRepository repository;

    @Override
    public void CreateOrganization(Organization organization) {
        System.out.println("Organization created - Service");
    }

    @Override
    public Organization OrganizationByID(String id) {
        return null;
    }

    @Override
    public Organization OrganizationByName(String name) {
        return null;
    }

    @Override
    public List<Organization> getAll() {
        return repository.findAll();
    }


}
