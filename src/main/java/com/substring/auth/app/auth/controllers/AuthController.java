package com.substring.auth.app.auth.controllers;

import com.substring.auth.app.auth.payload.JwtResponse;
import com.substring.auth.app.auth.payload.LoginRequest;
import com.substring.auth.app.auth.payload.RegisterRequest;
import com.substring.auth.app.auth.payload.UserDto;
import com.substring.auth.app.auth.services.AuthService;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Public authentication endpoints. Everything here is listed under
 * AppConstants.PUBLIC_URLS, so it is reachable WITHOUT a JWT token.
 */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@SecurityRequirements // no lock icon / no auth required in Swagger for these endpoints
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@Valid @RequestBody RegisterRequest request) {
        UserDto createdUser = authService.registerUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequest request) {
        JwtResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}
