package com.example.canteen.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.canteen.enity.FoodItem;
import com.example.canteen.repository.FoodItemRepository;

import java.util.List;

@RestController
@RequestMapping("/api/food")
@RequiredArgsConstructor
public class FoodController {

    private final FoodItemRepository foodItemRepository;
    @PostMapping
    public ResponseEntity<?> insert(@RequestBody FoodItem foodItem)
    {
        foodItemRepository.save(foodItem);
        return ResponseEntity.ok("success");
    }

    @GetMapping
    public List<FoodItem> getAllFoodItems() {
        return foodItemRepository.findAll();
    }
}