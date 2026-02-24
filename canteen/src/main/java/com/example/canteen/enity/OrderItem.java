package com.example.canteen.enity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    private FoodOrder order;

    @ManyToOne
    private FoodItem foodItem;

    private String foodName;   // snapshot
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public FoodOrder getOrder() {
        return order;
    }
    public void setOrder(FoodOrder order) {
        this.order = order;
    }
    public FoodItem getFoodItem() {
        return foodItem;
    }
    public void setFoodItem(FoodItem foodItem) {
        this.foodItem = foodItem;
    }
    public String getFoodName() {
        return foodName;
    }
    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public Double getSubtotal() {
        return subtotal;
    }
    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
    private Double price;      // snapshot
    private Integer quantity;
    private Double subtotal;
}