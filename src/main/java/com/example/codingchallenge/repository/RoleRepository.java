package com.example.codingchallenge.repository;

import com.example.codingchallenge.model.AdminRole;
import com.example.codingchallenge.model.Role;

public interface RoleRepository {
    boolean CreateRole(Role role);
    void CreateAdminRole(AdminRole adminRole);
}
