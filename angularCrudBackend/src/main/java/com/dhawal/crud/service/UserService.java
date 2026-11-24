
package com.dhawal.crud.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dhawal.crud.dto.UserDTO;
import com.dhawal.crud.entity.User;
import com.dhawal.crud.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

        @Value("${image.upload-dir}")
        private String uploadDir;

        @Autowired
        private UserRepository userRepository;

        public List<UserDTO> getAllUsers() {
                return userRepository.findAll().stream()
                                .map(user -> new UserDTO(
                                                user.getId(),
                                                user.getFirstName(),
                                                user.getLastName(),
                                                user.getEmail(),
                                                user.getProfileImage(),
                                                user.getActive()))
                                .collect(Collectors.toList());
        }

        public UserDTO getUserById(Long id) {
                User user = userRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

                return new UserDTO(
                                user.getId(),
                                user.getFirstName(),
                                user.getLastName(),
                                user.getEmail(),
                                user.getProfileImage(),
                                user.getActive());
        }

        public void deleteUserById(Long id) {
                User user = userRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
                userRepository.delete(user);
        }

        @Transactional
        public UserDTO saveUser(User user, MultipartFile profileImage) throws IOException {

                Map<String, Object> response = new HashMap<>();
                try {
                        if (profileImage != null && !profileImage.isEmpty()) {
                                String fileName = System.currentTimeMillis() + "_" + profileImage.getOriginalFilename();
                                Path filePath = Paths.get(uploadDir, fileName);

                                Files.createDirectories(Paths.get(uploadDir));
                                profileImage.transferTo(filePath.toFile());

                                user.setProfileImage(fileName);
                        }

                        User savedUser = userRepository.save(user);
                        response.put("status", "success");
                        response.put("data", new UserDTO(
                                        savedUser.getId(),
                                        savedUser.getFirstName(),
                                        savedUser.getLastName(),
                                        savedUser.getEmail(),
                                        savedUser.getProfileImage(),
                                        savedUser.getActive()));
                        response.put("message", "User created successfully");
                        response.put("timestamp", LocalDateTime.now());
                        return new UserDTO(
                                        savedUser.getId(),
                                        savedUser.getFirstName(),
                                        savedUser.getLastName(),
                                        savedUser.getEmail(),
                                        savedUser.getProfileImage(),
                                        savedUser.getActive());

                } catch (IOException e) {
                        response.put("status", "fail");
                        response.put("message", "Error saving user: " + e.getMessage());
                        response.put("timestamp", LocalDateTime.now());
                        throw new RuntimeException("Error saving user: " + e.getMessage(), e);
                }
        }

        @Transactional
        public UserDTO updateUser(Long id, User updatedUser, MultipartFile profileImage) throws IOException {

                Map<String, Object> response = new HashMap<>();
                try {
                        User existingUser = userRepository.findById(id)
                                        .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

                        existingUser.setFirstName(updatedUser.getFirstName());
                        existingUser.setLastName(updatedUser.getLastName());
                        existingUser.setEmail(updatedUser.getEmail());

                        if (profileImage != null && !profileImage.isEmpty()) {
                                String fileName = System.currentTimeMillis() + "_" + profileImage.getOriginalFilename();
                                Path filePath = Paths.get(uploadDir, fileName);

                                Files.createDirectories(Paths.get(uploadDir));
                                profileImage.transferTo(filePath.toFile());
                                existingUser.setProfileImage(fileName);
                        }

                        User savedUser = userRepository.save(existingUser);
                        response.put("status", "success");
                        response.put("data", new UserDTO(
                                        savedUser.getId(),
                                        savedUser.getFirstName(),
                                        savedUser.getLastName(),
                                        savedUser.getEmail(),
                                        savedUser.getProfileImage(),
                                        savedUser.getActive()));
                        response.put("message", "User updated successfully");
                        response.put("timestamp", LocalDateTime.now());
                        return new UserDTO(
                                        savedUser.getId(),
                                        savedUser.getFirstName(),
                                        savedUser.getLastName(),
                                        savedUser.getEmail(),
                                        savedUser.getProfileImage(),
                                        savedUser.getActive());

                } catch (IOException e) {
                        response.put("status", "fail");
                        response.put("message", "Error updating user: " + e.getMessage());
                        response.put("timestamp", LocalDateTime.now());
                        throw new RuntimeException("Error updating user: " + e.getMessage(), e);
                }

        }

}