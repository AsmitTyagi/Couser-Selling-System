package com.example.user.controller;

import com.example.user.dto.*;
import com.example.user.model.User;
import com.example.user.service.JwtService;
import com.example.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO) {
//        UserDTO registered = userService.register(userDTO, userDTO.getRole().equalsIgnoreCase("ADMIN") ? "adminpass" : "userpass");
//        return ResponseEntity.ok(registered);
        
        UserDTO registered = userService.register(userDTO, userDTO.getPassword());
        return ResponseEntity.ok(registered);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest request) {
        User user = userService.findByEmail(request.getEmail());
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.badRequest().build();
        }
        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(new JwtResponse(token, user.getEmail(), user.getRole()));
    }
    
}
