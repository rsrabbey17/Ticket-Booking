package com.ticketbooking.controller;

import com.ticketbooking.dto.request.LoginRequest;
import com.ticketbooking.dto.request.RegisterRequest;
import com.ticketbooking.dto.response.ApiResponse;
import com.ticketbooking.dto.response.LoginResponse;
import com.ticketbooking.security.JwtUtil;
import com.ticketbooking.service.CustomUserDetailsService;
import com.ticketbooking.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
        final var user = userService.findByEmail(request.getEmail());

        return ResponseEntity.ok(ApiResponse.success(
                new LoginResponse(jwt, user.getEmail(), user.getFullName())
        ));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>> register(@Valid @RequestBody RegisterRequest request) {
        try {
            var user = userService.registerUser(request);
            user.setPassword(null);
            return ResponseEntity.ok(ApiResponse.success("Registration successful", user));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }
}