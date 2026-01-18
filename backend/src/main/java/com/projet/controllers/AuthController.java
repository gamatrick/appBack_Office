package com.projet.controllers;

import com.projet.entities.User;
import com.projet.repository.UserRepository;
import com.projet.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;

    // POST /api/auth/register - Inscription utilisateur
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return "Erreur : Nom d'utilisateur déjà pris !";
        }
        user.setPassword(encoder.encode(user.getPassword()));
        // Par défaut, on peut forcer le rôle USER si vide
        if (user.getRoles() == null) user.setRoles("ROLE_USER");
        user.setEnabled(true);
        userRepository.save(user);
        return "Utilisateur enregistré avec succès !";
    }

    // POST /api/auth/login - Connexion
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody User user) {
        User u = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        if (encoder.matches(user.getPassword(), u.getPassword())) {
            String token = jwtUtils.generateToken(u.getUsername());
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            response.put("username", u.getUsername());
            response.put("role", u.getRoles());
            return response;
        } else {
            throw new RuntimeException("Mot de passe incorrect");
        }
    }
}