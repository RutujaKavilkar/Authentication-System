package com.substring.auth.app.auth.services.impl;

import com.substring.auth.app.auth.config.AppConstants;
import com.substring.auth.app.auth.entities.Role;
import com.substring.auth.app.auth.entities.User;
import com.substring.auth.app.auth.payload.JwtResponse;
import com.substring.auth.app.auth.payload.LoginRequest;
import com.substring.auth.app.auth.payload.RegisterRequest;
import com.substring.auth.app.auth.payload.UserDto;
import com.substring.auth.app.auth.repositories.RoleRepository;
import com.substring.auth.app.auth.repositories.UserRepository;
import com.substring.auth.app.auth.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public UserDto registerUser(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("A user with this email already exists");
        }

        Role defaultRole = roleRepository.findByName(AppConstants.ROLE_USER)
                .orElseThrow(() -> new IllegalStateException(
                        AppConstants.ROLE_USER + " not found. Application did not start up correctly."));

        Set<Role> roles = new HashSet<>();
        roles.add(defaultRole);

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())) // ✅ BCrypt hashing
                .enabled(true)
                .roles(roles)
                .build();

        User savedUser = userRepository.save(user);

        return toDto(savedUser);
    }

    @Override
    public JwtResponse login(LoginRequest request) {

        Authentication authentication;
        try {
            // Delegates to CustomUserDetailService + PasswordEncoder under the hood.
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid email or password");
        }

        User user = (User) authentication.getPrincipal();

        String token = jwtService.generateToken(user);

        return JwtResponse.builder()
                .token(token)
                .tokenType("Bearer")
                .user(toDto(user))
                .build();
    }

    private UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId().toString());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setEnabled(user.isEnabled());
        dto.setRoles(user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()));
        return dto;
    }
}
