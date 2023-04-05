package com.jwt.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;
}
