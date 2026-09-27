package com.substring.auth.app.auth.controllers;

import com.substring.auth.app.auth.entities.User;
import com.substring.auth.app.auth.payload.UserDto;
import com.substring.auth.app.auth.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Every endpoint in this controller REQUIRES a valid "Authorization: Bearer <token>" header
 * (see SecurityConfig -> anyRequest().authenticated()).
 */
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /** Returns the profile of whoever the JWT belongs to. Any authenticated user (USER or ADMIN) can call this. */
    @GetMapping("/me")
    public ResponseEntity<UserDto> getCurrentUser(@AuthenticationPrincipal User currentUser) {
        return ResponseEntity.ok(userService.getUserByEmail(currentUser.getEmail()));
    }

    /** ADMIN-only: list every registered user. */
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    /** ADMIN-only: fetch any user by id. */
    @GetMapping("/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDto> getUserById(@PathVariable String userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    /** ADMIN-only: delete a user. */
    @DeleteMapping("/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
