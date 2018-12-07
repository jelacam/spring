package com.example.codingchallenge.controller;

import com.example.codingchallenge.model.Permission;
import com.example.codingchallenge.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;

@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping(value = "/get/{id}", method =  RequestMethod.GET)
    public Permission GetPermissionById(@PathVariable String id){
        return permissionService.FindById(id);
    }

    @RequestMapping(value = "/create", method =  RequestMethod.POST)
    public void CreatePermission(@ModelAttribute Permission permission){
        permissionService.CreatePermission(permission);
    }

}
