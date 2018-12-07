package com.example.codingchallenge.service.Impl;

import com.example.codingchallenge.model.Permission;
import com.example.codingchallenge.repository.PermissionRepository;
import com.example.codingchallenge.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public void CreatePermission(Permission permission) {
        String id = UUID.randomUUID().toString();
        permission.setId(id);
        permissionRepository.CreatePermission(permission);
    }

    @Override
    public Permission FindById(String id) {
        return permissionRepository.FindById(id);
    }
}
