package com.paybackpal.backend.paybackpal.dto;

import lombok.Getter;

@Getter
public class LoginUserReq {
    private String email;
    private String password;
}
