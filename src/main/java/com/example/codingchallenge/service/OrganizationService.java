package com.example.codingchallenge.service;

import com.example.codingchallenge.model.Organization;
import java.util.List;

public interface OrganizationService {

    void CreateOrganization(Organization organization);
    Organization OrganizationByID(String id);
    Organization OrganizationByName(String name);
    List<Organization> getAll();

    boolean UpdateOrganization(Organization organization);

    boolean DeleteOrganization(String id);
}
