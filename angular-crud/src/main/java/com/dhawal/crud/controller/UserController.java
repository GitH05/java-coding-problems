package com.dhawal.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhawal.crud.dto.UserDTO;
import com.dhawal.crud.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController 
@RequestMapping("api/users")
public class UserController {

    @Autowired 
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    
}

