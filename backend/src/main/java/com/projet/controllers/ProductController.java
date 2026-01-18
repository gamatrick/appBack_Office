package com.projet.controllers;

import com.projet.entities.Product;
import com.projet.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    // Recherche par nom
    @GetMapping("/search")
    public List<Product> search(@RequestParam String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    // ajout d'un produit (seulement l'admin)
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Product add(@RequestBody Product product) {
        return productRepository.save(product);
    }

    // supprime un produit (seulement l'admin)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        productRepository.deleteById(id);
    }
}