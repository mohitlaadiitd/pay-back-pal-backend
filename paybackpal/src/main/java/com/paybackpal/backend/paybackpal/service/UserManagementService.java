package com.paybackpal.backend.paybackpal.service;

import com.paybackpal.backend.paybackpal.dto.CreateUserReq;
import com.paybackpal.backend.paybackpal.entity.UserDetails;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserManagementService {

    ResponseEntity<List<UserDetails>> getUsers();

    ResponseEntity<String> createUser(CreateUserReq createUserReq);
}
