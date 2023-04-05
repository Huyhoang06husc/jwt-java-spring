package com.jwt.demo.service;

import com.jwt.demo.model.User;

public interface UserService {

    User findByUsername(String username);

//    User findByUsernameAndPassword(String username, String password);
}
