package com.dhawal.registerLogin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhawal.registerLogin.dto.EmployeeDTO;
import com.dhawal.registerLogin.dto.LogInDto;
import com.dhawal.registerLogin.response.LoginResponse;
import com.dhawal.registerLogin.service.EmployeeService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveEmployee(
            @RequestBody EmployeeDTO employeeDTO) {

        String response = employeeService.addEmployee(employeeDTO);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginEmployee(
            @RequestBody LogInDto logInDto) {

        LoginResponse loginResponse =
                employeeService.loginEmployee(logInDto);

        return ResponseEntity.ok(loginResponse);
    }
}