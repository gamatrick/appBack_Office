package com.projet.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders") // "order" est un mot réservé en SQL, on utilise "orders"
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // L'utilisateur qui a passé la commande

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product; // Le produit commandé

    private Integer quantity;
    private Double totalAmount;

    // Status demandés : PENDING, PROCESSING, SHIPPED, DELIVERED
    private String status;

    private LocalDateTime orderDate = LocalDateTime.now();
}