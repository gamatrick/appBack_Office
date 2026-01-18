package com.projet.repository;

import com.projet.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Utilisé par JwtUtils et AuthController pour la connexion
    Optional<User> findByUsername(String username);
}