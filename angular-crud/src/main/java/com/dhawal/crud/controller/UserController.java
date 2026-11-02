package com.dhawal.crud.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dhawal.crud.dto.UserDTO;
import com.dhawal.crud.entity.User;
import com.dhawal.crud.service.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<?> getAllUsers() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<UserDTO> users = userService.getAllUsers();
            response.put("status", "success");
            response.put("data", users);
            response.put("message", "Users retrieved successfully");
            response.put("timestamp", LocalDateTime.now());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            response.put("status", "fail");
            response.put("message", e.getMessage());
            response.put("timestamp", LocalDateTime.now());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        try {
            UserDTO user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("status", 404);
            response.put("message", e.getMessage());
            response.put("timestamp", LocalDateTime.now());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            userService.deleteUserById(id);
            response.put("status", "success");
            response.put("message", "User deleted successfully");
            response.put("timestamp", LocalDateTime.now());
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (RuntimeException e) {
            response.put("status", "fail");
            response.put("message", e.getMessage());
            response.put("timestamp", LocalDateTime.now());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<UserDTO> createuser(
            @RequestParam("first_name") String firstName,
            @RequestParam("last_name") String lastName,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam(value = "image", required = false) MultipartFile profileImage) throws IOException {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);

        UserDTO createdUser = userService.saveUser(user, profileImage);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

}