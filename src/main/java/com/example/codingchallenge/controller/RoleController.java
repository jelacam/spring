package com.example.codingchallenge.controller;

import com.example.codingchallenge.model.AdminRole;
import com.example.codingchallenge.model.Role;
import com.example.codingchallenge.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PreAuthorize("hasPermission(#role.id, 'ROLE', 'CREATE')")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public boolean CreateRole(@ModelAttribute Role role){
        return roleService.CreateRole(role);
    }

    @PreAuthorize("hasPermission(new com.example.codingchallenge.model.Admin(), 'CREATE')")
    @RequestMapping(value = "/adminrole", method = RequestMethod.POST)
    public void BindAdminRole(@ModelAttribute AdminRole adminRole){
        roleService.CreateAdminRole(adminRole);
    }

    @PreAuthorize("hasPermission(#id, 'ROLE', 'READ')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Role GetRole(@PathVariable String id){
        return roleService.FindById(id);
    }

    @PreAuthorize("hasPermission(#role.id, 'ROLE', 'UPDATE')")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public boolean UpdateRole(@ModelAttribute Role role){
        return roleService.UpdateRole(role);
    }

    public boolean DeleteRole(@PathVariable String id){
        return roleService.DeleteRole(id);
    }
}
