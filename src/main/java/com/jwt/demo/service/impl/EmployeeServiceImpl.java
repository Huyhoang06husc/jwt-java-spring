package com.jwt.demo.service.impl;

import com.jwt.demo.exception.EmployeeException;
import com.jwt.demo.model.Employee;
import com.jwt.demo.repository.EmployeeRepository;
import com.jwt.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAllEmployee() {

        List<Employee> employeeList = employeeRepository.findAll();
        if (null == employeeList){
            throw new EmployeeException("List is empty");
        }
        return employeeList;
    }

    @Override
    public Employee findById(Long id) {
        Employee employee = employeeRepository.findByIdEmployee(id);
        if (null == employee){
            throw new EmployeeException("employee not found");
        }
        return employee;
    }

    @Override
    public Employee insert(Employee employee) {
        return employeeRepository.save(employee);
    }
}
