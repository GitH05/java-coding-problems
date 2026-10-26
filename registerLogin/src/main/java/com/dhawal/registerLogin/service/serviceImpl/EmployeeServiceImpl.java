package com.dhawal.registerLogin.service.serviceImpl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dhawal.registerLogin.dto.EmployeeDTO;
import com.dhawal.registerLogin.dto.LogInDto;
import com.dhawal.registerLogin.entity.Employee;
import com.dhawal.registerLogin.repository.EmployeeRepository;
import com.dhawal.registerLogin.response.LoginResponse;
import com.dhawal.registerLogin.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    public EmployeeServiceImpl(
            EmployeeRepository employeeRepository,
            PasswordEncoder passwordEncoder) {

        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String addEmployee(EmployeeDTO employeeDTO) {

        // Check if email already exists
        Employee existingEmployee =
                employeeRepository.findByEmail(employeeDTO.getEmail());

        if (existingEmployee != null) {
            return "Email already exists!";
        }

        Employee employee = new Employee(
                null,
                employeeDTO.getEmployeeName(),
                employeeDTO.getEmail(),
                passwordEncoder.encode(employeeDTO.getPassword())
        );

        employeeRepository.save(employee);

        return "Employee added successfully with ID: "
                + employee.getEmployeeId()
                + " and Name: "
                + employee.getEmployeeName();
    }

    @Override
    public LoginResponse loginEmployee(LogInDto logInDto) {

        Employee employee =
                employeeRepository.findByEmail(logInDto.getEmail());

        if (employee == null) {
            return new LoginResponse(
                    "Email does not exist!",
                    "Failure"
            );
        }

        boolean passwordMatches =
                passwordEncoder.matches(
                        logInDto.getPassword(),
                        employee.getPassword()
                );

        if (passwordMatches) {
            return new LoginResponse(
                    "Login successful",
                    "Success"
            );
        }

        return new LoginResponse(
                "Invalid password!",
                "Failure"
        );
    }
}