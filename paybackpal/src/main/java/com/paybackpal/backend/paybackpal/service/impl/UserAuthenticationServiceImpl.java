package com.paybackpal.backend.paybackpal.service.impl;

import com.paybackpal.backend.paybackpal.dto.LoginUserReq;
import com.paybackpal.backend.paybackpal.entity.UserDetails;
import com.paybackpal.backend.paybackpal.repository.UserManagementRepository;
import com.paybackpal.backend.paybackpal.service.UserAuthenticationService;
import com.paybackpal.backend.paybackpal.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

    @Autowired
    private UserManagementRepository userManagementRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public ResponseEntity<String> loginUser(LoginUserReq loginUserReq) {
        try {
            UserDetails userDetails = userManagementRepository.findByEmail(loginUserReq.getEmail());
            if (userDetails != null) {
                if (bCryptPasswordEncoder.matches(loginUserReq.getPassword(), userDetails.getPassword())) {
                    String jwt = jwtUtil.generateToken(loginUserReq.getEmail());
                    return new ResponseEntity<>(jwt, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("Incorrect combination of email and password", HttpStatus.UNAUTHORIZED);
                }
            }
            return new ResponseEntity<>("User not found with the provided email", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error logging in the user " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}















