package com.example.codingchallenge.controller;

import com.example.codingchallenge.model.Admin;
import com.example.codingchallenge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/create/admin", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createAdmin(@ModelAttribute Admin admin){
        userService.createAdminUser(admin);

    }

    @PreAuthorize("hasPermission(#id, 'ADMIN', 'READ')")
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Admin findById(@PathVariable String id){
        return userService.findById(id);
    }

    @PreAuthorize("hasPermission(#admin.id, 'ADMIN', 'UPDATE')")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Admin updateAdmin(@ModelAttribute Admin admin) {
        return userService.updateAdmin(admin);
    }

    @PreAuthorize("hasPermission(#id, 'ADMIN', 'DELETE')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public boolean deleteAdmin(@PathVariable String id) {
        return userService.deleteAdmin(id);
    }
}
