package com.dhawal.registerLogin.service;

import com.dhawal.registerLogin.dto.EmployeeDTO;
import com.dhawal.registerLogin.dto.LogInDto;
import com.dhawal.registerLogin.response.LoginResponse;

public interface EmployeeService {
    String addEmployee(EmployeeDTO employeeDTO);

    LoginResponse loginEmployee(LogInDto logInDto);
}
