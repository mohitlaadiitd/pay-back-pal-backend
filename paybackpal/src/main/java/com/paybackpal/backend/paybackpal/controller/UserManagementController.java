package com.paybackpal.backend.paybackpal.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserManagementController {

    @GetMapping(path = "/hello")
    public String helloMethod(@RequestParam String name) {
        return "Hello " + name;
    }


}
