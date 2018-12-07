package com.example.codingchallenge.service.Impl;

import com.example.codingchallenge.model.Admin;
import com.example.codingchallenge.repository.AdminRepository;
import com.example.codingchallenge.securityconfig.SHA256Helper;
import com.example.codingchallenge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public boolean createAdminUser(Admin admin) {
        // generate id when storing into database
        String id = UUID.randomUUID().toString();
        admin.setId(id);

        // has password using SHA256 algorithm
        String passwordHash = SHA256Helper.Generate(admin.getPassword());
        admin.setPassword(passwordHash);

        return adminRepository.createAdminUser(admin);
    }

    @Override
    public Admin findById(String id) {
        return adminRepository.findAdmin(id);
    }

    @Override
    public Admin updateAdmin(Admin admin) {
        String hashedPassword = SHA256Helper.Generate(admin.getPassword());
        admin.setPassword(hashedPassword);
        return adminRepository.updateAdmin(admin);
    }

    @Override
    public boolean deleteAdmin(String id) {
        return adminRepository.deleteAdmin(id);
    }


}
