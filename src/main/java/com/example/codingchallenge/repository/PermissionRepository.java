package com.example.codingchallenge.repository;


import com.example.codingchallenge.model.Permission;

import java.util.List;

public interface PermissionRepository {
    boolean CreatePermission(Permission permission);
    Permission FindById(String id);
    List<Permission> FindByRoleId(String id);

    List<Permission> FindByAdminId(String id);
}
