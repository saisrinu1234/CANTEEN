package com.example.canteen.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.canteen.enity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {}