package com.substring.auth.app.auth.services.impl;

import com.substring.auth.app.auth.entities.Role;
import com.substring.auth.app.auth.entities.User;
import com.substring.auth.app.auth.payload.UserDto;
import com.substring.auth.app.auth.repositories.UserRepository;
import com.substring.auth.app.auth.services.UserService;
import com.substring.auth.app.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
        return toDto(user);
    }

    @Override
    public UserDto getUserById(String userId) {
        User user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        return toDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(String userId) {
        User user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        userRepository.delete(user);
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
