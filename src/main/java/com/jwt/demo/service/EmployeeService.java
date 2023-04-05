package com.jwt.demo.service;

import com.jwt.demo.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAllEmployee();

    Employee findById(Long id);

    Employee insert(Employee employee);
}
