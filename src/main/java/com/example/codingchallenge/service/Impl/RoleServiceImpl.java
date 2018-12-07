package com.example.codingchallenge.service.Impl;

import com.example.codingchallenge.model.AdminRole;
import com.example.codingchallenge.model.Role;
import com.example.codingchallenge.repository.RoleRepository;
import com.example.codingchallenge.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public boolean CreateRole(Role role) {
        String id = UUID.randomUUID().toString();
        role.setId(id);
        return roleRepository.CreateRole(role);
    }

    @Override
    public void CreateAdminRole(AdminRole adminRole) {
        String id = UUID.randomUUID().toString();
        adminRole.setId(id);
        roleRepository.CreateAdminRole(adminRole);
    }
}
