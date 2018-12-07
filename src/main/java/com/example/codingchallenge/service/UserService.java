package com.example.codingchallenge.service;

import com.example.codingchallenge.model.Admin;

public interface UserService {
    boolean createAdminUser(Admin admin);
    Admin findById(String id);
    Admin updateAdmin(Admin admin);
    boolean deleteAdmin(String id);
}
