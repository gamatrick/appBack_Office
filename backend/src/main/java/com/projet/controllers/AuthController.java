package com.projet.controllers;

import com.projet.entities.User;
import com.projet.repository.UserRepository;
import com.projet.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        // On encode le mot de passe avant de sauvegarder
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Par défaut, on donne le rôle USER (en dur comme demandé)
        if (user.getRoles() == null) user.setRoles("ROLE_USER");
        userRepository.save(user);
        return "Utilisateur enregistré avec succès !";
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody User loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            String token = jwtUtils.generateToken(user.getUsername());
            return Map.of("token", token, "roles", user.getRoles());
        } else {
            throw new RuntimeException("Mot de passe incorrect");
        }
    }
}