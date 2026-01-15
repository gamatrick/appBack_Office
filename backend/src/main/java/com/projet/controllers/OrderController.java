package com.projet.controllers;

import com.projet.entities.Order;
import com.projet.entities.User;
import com.projet.repository.OrderRepository;
import com.projet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired private OrderRepository orderRepository;
    @Autowired private UserRepository userRepository;

    @PostMapping
    public Order createOrder(@RequestBody Order order, Principal principal) {
        // On récupère l'utilisateur connecté grâce au Token JWT
        User user = userRepository.findByUsername(principal.getName()).orElseThrow();
        order.setUser(user);
        order.setStatus("PENDING");
        return orderRepository.save(order);
    }

    @GetMapping("/my-orders")
    public List<Order> getMyOrders(Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow();
        return orderRepository.findByUser(user);
    }
}