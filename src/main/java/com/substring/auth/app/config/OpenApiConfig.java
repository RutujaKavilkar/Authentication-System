package com.substring.auth.app.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger / OpenAPI configuration.
 * Adds a "bearerAuth" scheme so you can click "Authorize" in Swagger UI
 * and paste the JWT access token to call protected APIs.
 *
 * Swagger UI : http://localhost:8080/swagger-ui.html
 * OpenAPI doc: http://localhost:8080/v3/api-docs
 */
@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Spring Boot JWT Authentication API",
                version = "v1",
                description = "Register / Login / JWT protected REST API built with Spring Boot 3, Spring Security 6, JWT (JJWT) and MySQL."
        ),
        security = @SecurityRequirement(name = "bearerAuth")
)
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class OpenApiConfig {
}
