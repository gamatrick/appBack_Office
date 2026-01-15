package com.projet.repository;

import com.projet.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Pour la recherche par nom (demandé dans tes consignes)
    List<Product> findByNameContainingIgnoreCase(String name);

    // Pour la recherche par catégorie
    List<Product> findByCategoryIgnoreCase(String category);

    // Pour la liste paginée (demandé pour le ProductController)
    Page<Product> findAll(Pageable pageable);
}