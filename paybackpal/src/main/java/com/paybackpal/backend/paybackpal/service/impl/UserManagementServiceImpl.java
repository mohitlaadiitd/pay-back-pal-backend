package com.paybackpal.backend.paybackpal.service.impl;

import com.paybackpal.backend.paybackpal.dto.CreateUserReq;
import com.paybackpal.backend.paybackpal.entity.UserDetails;
import com.paybackpal.backend.paybackpal.repository.UserManagementRepository;
import com.paybackpal.backend.paybackpal.service.UserManagementService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class UserManagementServiceImpl implements UserManagementService {

    @Autowired
    private UserManagementRepository userManagementRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(UserManagementServiceImpl.class);

    @Override
    public ResponseEntity<List<UserDetails>> getUsers() {
        return new ResponseEntity<>(userManagementRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> createUser(CreateUserReq createUserReq) {
        try {
            UserDetails existingUser = userManagementRepository.findByPhoneOrEmail(createUserReq.getPhone(), createUserReq.getEmail());
            if (existingUser != null) {
                return new ResponseEntity<>("User with email or phone already exists", HttpStatus.CONFLICT);
            }
            if (validateUserReq(createUserReq)) {
                UserDetails user = getUserDetailObject(createUserReq);
                userManagementRepository.save(user);
                return new ResponseEntity<>("User saved successfully!", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Request body lagging required fields", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating the user " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private boolean validateUserReq(CreateUserReq createUserReq) {
        return createUserReq != null &&
                StringUtils.isNoneBlank(createUserReq.getFirstName(), createUserReq.getFirstName(), createUserReq.getPhone(),
                        createUserReq.getEmail(), createUserReq.getPassword());
    }

    private UserDetails getUserDetailObject(CreateUserReq createUserReq) {
        UserDetails user = new UserDetails();
        user.setFirstName(createUserReq.getFirstName());
        user.setLastName(createUserReq.getLastName());
        user.setEmail(createUserReq.getEmail());
        user.setPhone(createUserReq.getPhone());
        user.setPassword(bCryptPasswordEncoder.encode(createUserReq.getPassword()));
        user.setActive(true);
        user.setCreatedAt(Date.from(Instant.now()));
        user.setUpdatedAt(Date.from(Instant.now()));
        return user;
    }
}