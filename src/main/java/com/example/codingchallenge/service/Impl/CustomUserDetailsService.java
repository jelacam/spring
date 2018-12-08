package com.example.codingchallenge.service.Impl;

import com.example.codingchallenge.model.Admin;
import com.example.codingchallenge.repository.AdminRepository;
import com.example.codingchallenge.repository.PermissionRepository;
import com.example.codingchallenge.securityconfig.CustomPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findAdminByUsername(username);
        if (admin == null) {
            throw new UsernameNotFoundException(username);
        }
        admin.setPermissions(permissionRepository.FindByAdminId(admin.getId()));
        return new CustomPrincipal(admin);

    }
}
