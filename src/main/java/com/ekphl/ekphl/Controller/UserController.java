package com.ekphl.ekphl.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ekphl.ekphl.Models.User;
import com.ekphl.ekphl.Repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/check-duplicates")
    public ResponseEntity<Map<String, Boolean>> checkDuplicates(@RequestBody Map<String, String> payload) {
        Map<String, Boolean> response = new HashMap<>();

        response.put("userNameExists", userRepository.existsByUserName(payload.get("userName")));
        response.put("emailExists", userRepository.existsByEmail(payload.get("email")));
        response.put("controlNumberExists", userRepository.existsByControlNumber(payload.get("controlNumber")));
        response.put("plateNumberExists", userRepository.existsByPlateNumber(payload.get("plateNumber")));

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        if (userRepository.existsByUserName(user.getUserName()) ||
            userRepository.existsByEmail(user.getEmail()) ||
            userRepository.existsByControlNumber(user.getControlNumber()) ||
            userRepository.existsByPlateNumber(user.getPlateNumber())) {
            return ResponseEntity.badRequest().body("Duplicate entry found. Please check your details.");
        }

        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully!");
    }
}
