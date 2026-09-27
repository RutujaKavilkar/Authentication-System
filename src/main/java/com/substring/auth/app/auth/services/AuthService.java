package com.substring.auth.app.auth.services;

import com.substring.auth.app.auth.payload.JwtResponse;
import com.substring.auth.app.auth.payload.LoginRequest;
import com.substring.auth.app.auth.payload.RegisterRequest;
import com.substring.auth.app.auth.payload.UserDto;

public interface AuthService {

    UserDto registerUser(RegisterRequest request);

    JwtResponse login(LoginRequest request);
}
