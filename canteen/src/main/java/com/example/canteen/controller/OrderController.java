// package com.example.canteen.controller;

// import lombok.RequiredArgsConstructor;
// import org.springframework.web.bind.annotation.*;

// import com.example.canteen.dto.OrderRequest;
// import com.example.canteen.enity.FoodOrder;
// import com.example.canteen.repository.OrderRepository;
// import com.example.canteen.service.OrderService;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.Map;
// import java.util.UUID;

// @RestController
// @RequestMapping("/api/orders")
// @RequiredArgsConstructor
// public class OrderController {

//     private final OrderService orderService;
//     private final OrderRepository orderRepository;
//     @PostMapping
//     public Map<String, Object> createOrder(@RequestBody Map<String, Object> request) throws Exception {

//         UUID userId = UUID.fromString((String) request.get("userId"));

//         List<Map<String, Object>> itemsRaw = (List<Map<String, Object>>) request.get("items");

//         List<OrderRequest> items = new ArrayList<>();

//         for (Map<String, Object> item : itemsRaw) {
//             OrderRequest req = new OrderRequest();
//             req.setFoodItemId(UUID.fromString((String) item.get("foodItemId")));
//             req.setQuantity((Integer) item.get("quantity"));
//             items.add(req);
//         }

//         return orderService.placeOrder(userId, items);
//     }

//     @GetMapping("/{orderId}")
//     public Map<String, Object> getOrder(@PathVariable UUID orderId) {

//         FoodOrder order = orderRepository.findById(orderId)
//                 .orElseThrow();

//         return Map.of(
//                 "orderId", order.getId(),
//                 "status", order.getStatus(),
//                 "totalAmount", order.getTotalAmount(),
//                 "items", order.getItems());
//     }

// }