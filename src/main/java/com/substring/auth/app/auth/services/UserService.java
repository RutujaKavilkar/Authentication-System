package com.substring.auth.app.auth.services;

import com.substring.auth.app.auth.payload.UserDto;

import java.util.List;

public interface UserService {

    UserDto getUserByEmail(String email);

    UserDto getUserById(String userId);

    List<UserDto> getAllUsers();

    void deleteUser(String userId);
}
