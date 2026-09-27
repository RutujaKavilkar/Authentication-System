package com.substring.auth.app.auth.config;

public class AppConstants {

    private AppConstants() {
    }

    // Endpoints that do NOT require a JWT token
    public static final String[] PUBLIC_URLS = {
            "/api/v1/auth/**",
            "/v3/api-docs/**",
            "/v3/api-docs.yaml",
            "/swagger-ui/**",
            "/swagger-ui.html"
    };

    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
}
