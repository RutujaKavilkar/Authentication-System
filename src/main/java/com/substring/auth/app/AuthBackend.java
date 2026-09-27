package com.substring.auth.app;

import com.substring.auth.app.auth.config.AppConstants;
import com.substring.auth.app.auth.entities.Role;
import com.substring.auth.app.auth.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class AuthBackend {

    public static void main(String[] args) {
        SpringApplication.run(AuthBackend.class, args);
    }

    /**
     * Seeds the default roles (ROLE_USER, ROLE_ADMIN) into the database on startup,
     * so registration always has a role to attach to a new user.
     */
    @Component
    @RequiredArgsConstructor
    static class RoleSeeder implements CommandLineRunner {

        private final RoleRepository roleRepository;

        @Override
        public void run(String... args) {
            seedRole(AppConstants.ROLE_USER);
            seedRole(AppConstants.ROLE_ADMIN);
        }

        private void seedRole(String roleName) {
            roleRepository.findByName(roleName).orElseGet(() -> {
                Role role = Role.builder().name(roleName).build();
                return roleRepository.save(role);
            });
        }
    }
}
