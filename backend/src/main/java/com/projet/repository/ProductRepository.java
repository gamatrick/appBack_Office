package com.projet.repository;

import com.projet.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Consigne : "Recherche par nom"
    List<Product> findByNameContainingIgnoreCase(String name);

    // Optionnel : Recherche par catégorie
    List<Product> findByCategoryId(Long categoryId);
}