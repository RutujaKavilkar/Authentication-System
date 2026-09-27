package com.substring.auth.app.auth.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class JwtResponse {

    private String token;

    @Builder.Default
    private String tokenType = "Bearer";

    private UserDto user;
}
