package com.example.codingchallenge.service.Impl;

import com.example.codingchallenge.model.Organization;
import com.example.codingchallenge.repository.OrganizationRepository;
import com.example.codingchallenge.repository.ProductSharingRepository;
import com.example.codingchallenge.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private ProductSharingRepository productSharingRepository;

    @Override
    public void CreateOrganization(Organization organization) {
        organizationRepository.Create(organization);
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
        return organizationRepository.findAll();
    }

    @Override
    public boolean UpdateOrganization(Organization organization) {
        return organizationRepository.UpdateOrganization(organization);
    }

    @Override
    public boolean DeleteOrganization(String id) {
        return organizationRepository.DeleteOrganization(id);
    }

}
