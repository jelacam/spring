package com.example.codingchallenge.repository;

import com.example.codingchallenge.model.AdminRole;
import com.example.codingchallenge.model.Role;

import java.util.List;

public interface RoleRepository {
    boolean CreateRole(Role role);
    Role FindRoleById(String id);
    void CreateAdminRole(AdminRole adminRole);
    List<AdminRole> FindAdminRoleByAdminId(String id);

}
