// package com.example.canteen.service;

// import java.util.List;
// import java.util.Map;
// import java.util.UUID;

// import org.json.JSONObject;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Service;

// import com.example.canteen.dto.OrderRequest;
// import com.example.canteen.enity.FoodItem;
// import com.example.canteen.enity.FoodOrder;
// import com.example.canteen.enity.OrderItem;
// import com.example.canteen.enity.OrderStatus;
// import com.example.canteen.enity.Payment;
// import com.example.canteen.enity.User;
// import com.example.canteen.login.UserRepository;
// import com.example.canteen.repository.FoodItemRepository;
// import com.example.canteen.repository.OrderItemRepository;
// import com.example.canteen.repository.OrderRepository;
// import com.example.canteen.repository.PaymentRepository;
// import com.razorpay.Order;
// import com.razorpay.RazorpayClient;

// import jakarta.transaction.Transactional;
// import lombok.RequiredArgsConstructor;

// @Service
// @RequiredArgsConstructor
// public class OrderService {

//     private final UserRepository userRepository;
//     private final FoodItemRepository foodItemRepository;
//     private final OrderRepository orderRepository;
//     private final OrderItemRepository orderItemRepository;
//     private final PaymentRepository paymentRepository;

//     @Value("${razorpay.key}")
//     private String key;

//     @Value("${razorpay.secret}")
//     private String secret;

//     @Transactional
//     public Map<String, Object> placeOrder(UUID userId, List<OrderRequest> items) throws Exception {

//         User user = userRepository.findById(userId).orElseThrow();

//         FoodOrder order = new FoodOrder();
//         order.setUser(user);
//         order.setStatus(OrderStatus.PAYMENT_PENDING);

//         orderRepository.save(order);

//         double total = 0;

//         for (OrderRequest req : items) {

//             FoodItem food = foodItemRepository.findById(req.getFoodItemId())
//                     .orElseThrow();

//             OrderItem item = new OrderItem();
//             item.setOrder(order);
//             item.setFoodItem(food);
//             item.setFoodName(food.getName());
//             item.setPrice(food.getPrice());
//             item.setQuantity(req.getQuantity());

//             double subtotal = food.getPrice() * req.getQuantity();
//             item.setSubtotal(subtotal);

//             total += subtotal;

//             orderItemRepository.save(item);
//         }

//         order.setTotalAmount(total);
//         orderRepository.save(order);

//         // Razorpay Order
//         RazorpayClient razorpay = new RazorpayClient(key, secret);

//         JSONObject options = new JSONObject();
//         options.put("amount", total * 100);
//         options.put("currency", "INR");
//         options.put("receipt", order.getId().toString());

//         Order razorpayOrder = razorpay.orders.create(options);

//         Payment payment = new Payment();
//         payment.setOrder(order);
//         payment.setRazorpayOrderId(razorpayOrder.get("id"));
//         payment.setAmount(total);

//         paymentRepository.save(payment);

//         return Map.of(
//                 "razorpayOrderId", razorpayOrder.get("id"),
//                 "amount", total,
//                 "key", key
//         );
//     }
// }