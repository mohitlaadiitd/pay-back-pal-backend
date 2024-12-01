package com.paybackpal.backend.paybackpal.controller;

import com.paybackpal.backend.paybackpal.dto.CreateUserReq;
import com.paybackpal.backend.paybackpal.dto.LoginUserReq;
import com.paybackpal.backend.paybackpal.service.UserAuthenticationService;
import com.paybackpal.backend.paybackpal.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/auth/api")
public class UserAuthenticationController {

    @Autowired
    private UserManagementService userManagementService;

    @Autowired
    private UserAuthenticationService userAuthenticationService;

    @PostMapping(path = "/login", consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<String> login(@RequestBody LoginUserReq loginUserReq) {
        return userAuthenticationService.loginUser(loginUserReq);
    }

    @PostMapping(path = "/signup", consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<String> signup(@RequestBody CreateUserReq createUserReq) {
        return userManagementService.createUser(createUserReq);
    }
}
