package com.example.codingchallenge.repository;

import com.example.codingchallenge.model.Organization;
import java.util.List;

public interface OrganizationRepository {
    List<Organization> findAll();
    void Create(Organization organization);

    boolean UpdateOrganization(Organization organization);

    boolean DeleteOrganization(String id);
}
