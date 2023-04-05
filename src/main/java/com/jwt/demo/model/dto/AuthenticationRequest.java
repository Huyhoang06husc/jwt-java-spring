package com.jwt.demo.model.dto;

import lombok.Data;

@Data
public class AuthenticationRequest {

    private String username;

    private String password;
}
