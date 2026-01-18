package com.projet.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double price;


    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private Integer stockQuantity;
    private String lienImage;

    private LocalDateTime createdAt = LocalDateTime.now();
}