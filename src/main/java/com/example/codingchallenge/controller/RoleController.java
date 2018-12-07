package com.example.codingchallenge.controller;

import com.example.codingchallenge.model.AdminRole;
import com.example.codingchallenge.model.Role;
import com.example.codingchallenge.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public boolean CreateRole(@ModelAttribute Role role){
        return roleService.CreateRole(role);
    }

    @RequestMapping(value = "/adminrole", method = RequestMethod.POST)
    public void BindAdminRole(@ModelAttribute AdminRole adminRole){
        roleService.CreateAdminRole(adminRole);
    }
}
