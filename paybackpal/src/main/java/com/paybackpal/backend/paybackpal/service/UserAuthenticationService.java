package com.paybackpal.backend.paybackpal.service;

import com.paybackpal.backend.paybackpal.dto.LoginUserReq;
import org.springframework.http.ResponseEntity;

public interface UserAuthenticationService {
    ResponseEntity<String> loginUser(LoginUserReq loginUserReq);
}
