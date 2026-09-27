package com.substring.auth.app.auth.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.UUID;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // e.g. "ROLE_USER" or "ROLE_ADMIN"
    @Column(nullable = false, unique = true)
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
