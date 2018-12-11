package com.example.codingchallenge.controller;

import com.example.codingchallenge.model.Permission;
import com.example.codingchallenge.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;

@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    // only admin with authority ADMIN_READ, ADMIN_CREATE, ADMIN_DELETE, ADMIN_UPDATE can access permissions ?
    //@PreAuthorize("hasPermission(#id, 'ADMIN', 'READ')")
    @RequestMapping(value = "/{id}", method =  RequestMethod.GET)
    public Permission GetPermissionById(@PathVariable String id){
        return permissionService.FindById(id);
    }

    //@PreAuthorize("hasPermission(new com.example.codingchallenge.model.Admin(), 'CREATE')")
    @RequestMapping(value = "/create", method =  RequestMethod.POST)
    public void CreatePermission(@ModelAttribute Permission permission){
        permissionService.CreatePermission(permission);
    }

}
