package com.paybackpal.backend.paybackpal.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserReq {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
}
