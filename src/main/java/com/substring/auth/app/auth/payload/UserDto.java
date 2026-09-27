package com.substring.auth.app.auth.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String id;
    private String name;
    private String email;

    // Accepted on register requests, but never sent back in responses.
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private boolean enabled;
    private Set<String> roles;
}
