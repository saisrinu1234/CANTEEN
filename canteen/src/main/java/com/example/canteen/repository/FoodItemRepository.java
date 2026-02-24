package com.example.canteen.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.canteen.enity.FoodItem;

public interface FoodItemRepository extends JpaRepository<FoodItem, UUID> {}