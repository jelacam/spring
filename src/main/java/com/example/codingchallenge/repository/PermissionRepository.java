package com.example.codingchallenge.repository;


import com.example.codingchallenge.model.Permission;

public interface PermissionRepository {
    boolean CreatePermission(Permission permission);
    Permission FindById(String id);
}
