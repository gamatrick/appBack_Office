package com.projet.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data @NoArgsConstructor @AllArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    private String roles; // Stock "ADMIN" ou "USER"
    private boolean enabled = true;
    private LocalDateTime createdAt = LocalDateTime.now();
}