package com.jwt.demo.controller;

import com.jwt.demo.model.Employee;
import com.jwt.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee){

        Employee employee1 = employeeService.insert(employee);

        if (null == employee1){
            return new ResponseEntity<>(employee1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(employee1, HttpStatus.OK);
    }

    @GetMapping("/{idEmployee}")
    public ResponseEntity<Employee> findByIdEmployee(@PathVariable("idEmployee") Long idEmployee){

        Employee employee = employeeService.findById(idEmployee);
        if (null == employee){
            return new ResponseEntity<>(employee, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
}
