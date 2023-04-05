package com.jwt.demo.controller;

import com.jwt.demo.exception.UserException;
import com.jwt.demo.jwt.JwtUtil;
import com.jwt.demo.model.User;
import com.jwt.demo.model.dto.AuthenticationRequest;
import com.jwt.demo.model.dto.AuthenticationResponse;
import com.jwt.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;


    @GetMapping(value = "/getUserByUsername/{username}", consumes = "application/json")
    public ResponseEntity<User> getUser(@PathVariable("username") String username){

        User user = userService.findByUsername(username);
        if (null == user){
            throw new UserException("user not found!!!");
        }
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetailOne = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        String jwt = jwtUtil.generateToken(userDetailOne);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @GetMapping("/user")
    public ResponseEntity<?> userInfo(@RequestHeader("Authorization") String authorizationHeader) {
        String jwt = authorizationHeader.substring(7);
        String username = jwtUtil.extractUsername(jwt);
        User user = userService.findByUsername(username);
        if (null == user){
            throw new UserException("user not found!!!");
        }
        return ResponseEntity.ok(user);
    }
}
