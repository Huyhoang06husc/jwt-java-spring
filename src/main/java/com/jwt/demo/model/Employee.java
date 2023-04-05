package com.jwt.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "EMPLOYEE")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmployee;

    private String nameEmployee;

    private Integer age;



}
