package com.paybackpal.backend.paybackpal.controller;

import com.paybackpal.backend.paybackpal.dto.CreateUserReq;
import com.paybackpal.backend.paybackpal.entity.UserDetails;
import com.paybackpal.backend.paybackpal.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserManagementController {

    @Autowired
    private UserManagementService userManagementService;

    @GetMapping(path = "/hello")
    public String helloMethod(@RequestParam String name) {
        return "Hello bro " + name;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<UserDetails>> getUsers() {
        return userManagementService.getUsers();
    }

    //    @CrossOrigin(value = )
    @PostMapping(path = "/create", consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<String> createUser(@RequestBody CreateUserReq createUserReq) {
        return userManagementService.createUser(createUserReq);
    }
}
