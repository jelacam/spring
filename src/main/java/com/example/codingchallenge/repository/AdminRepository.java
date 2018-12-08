package com.example.codingchallenge.repository;

import com.example.codingchallenge.model.Admin;

public interface AdminRepository {
    boolean createAdminUser(Admin admin);
    Admin updateAdmin(Admin admin);
    boolean deleteAdmin(String id);
    Admin findAdmin(String id);

    Admin findAdminByUsername(String username);
}
