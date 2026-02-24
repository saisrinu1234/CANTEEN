package com.example.canteen.dto;

import java.util.UUID;

public class OrderRequest {
    private UUID foodItemId;
    public UUID getFoodItemId() {
        return foodItemId;
    }
    public void setFoodItemId(UUID foodItemId) {
        this.foodItemId = foodItemId;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    private Integer quantity;
}