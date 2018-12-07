package com.example.codingchallenge.service;

import com.example.codingchallenge.model.Permission;

public interface PermissionService {
    void CreatePermission(Permission permission);
    Permission FindById(String id);
}
