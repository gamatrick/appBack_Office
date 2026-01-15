package com.projet.repository;

import com.projet.entities.Order;
import com.projet.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // Consigne : "Voir ses commandes" (Espace utilisateur)
    List<Order> findByUser(User user);
}