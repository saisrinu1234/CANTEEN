package com.example.canteen.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    // ✅ Place Order
    @PostMapping("/place")
    public ResponseEntity<Order> placeOrder(
            @RequestBody Order order,
            Principal principal) {

        // 🔐 Get logged-in user email from JWT
        String email = principal.getName();

        order.setUserEmail(email);

        Order savedOrder = orderService.saveOrder(order);

        return ResponseEntity.ok(savedOrder);
    }

    @GetMapping("/my/pending")
    public List<Order> getMyPendingOrders(Principal principal) {

        String email = principal.getName(); // ✅ comes from JWT

        return orderService.getPendingOrdersByUser(email);
    }

}