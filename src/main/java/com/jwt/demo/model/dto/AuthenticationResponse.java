package com.jwt.demo.model.dto;

import com.jwt.demo.model.Role;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
public class AuthenticationResponse {
    private final String jwt;

//    private String username;
//
//    private Collection<? extends GrantedAuthority> roles = new HashSet<>();
//
//    public AuthenticationResponse(String jwt, String username, Collection<? extends GrantedAuthority> roles) {
//        this.jwt = jwt;
//        this.username = username;
//        this.roles = roles;
//    }


    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }
}
