package com.example.codingchallenge.service;

import com.example.codingchallenge.model.AdminRole;
import com.example.codingchallenge.model.Role;

public interface RoleService {
    boolean CreateRole(Role role);
    void CreateAdminRole(AdminRole adminRole);
    Role FindById(String id);

    boolean UpdateRole(Role role);

    boolean DeleteRole(String id);
}
