package com.projet.controllers;

import com.projet.entities.User;
import com.projet.entities.Product;
import com.projet.repository.UserRepository;
import com.projet.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')") // Protection globale pour ce contrôleur
public class AdminController {

    @Autowired private UserRepository userRepository;
    @Autowired private ProductRepository productRepository;

    // Gestion des utilisateurs
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        User user = userRepository.findById(id).orElseThrow();
        user.setRoles(userDetails.getRoles());
        user.setEnabled(userDetails.isEnabled());
        return userRepository.save(user);
    }

    // CRUD Produits (Exemple : Suppression)
    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
    }
}