package com.dhawal.registerLogin.dto;

public class EmployeeDTO {

    private String employeeName;
    private String email;
    private String password;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String employeeName, String email, String password) {
        this.employeeName = employeeName;
        this.email = email;
        this.password = password;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}