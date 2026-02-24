package com.example.canteen.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.canteen.enity.FoodOrder;
import com.example.canteen.enity.OrderStatus;
import com.example.canteen.enity.Payment;
import com.example.canteen.enity.PaymentStatus;
import com.example.canteen.repository.OrderRepository;
import com.example.canteen.repository.PaymentRepository;
import com.razorpay.Utils;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/webhook")
public class RazorpayWebhookController {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    @Value("${razorpay.webhook.secret}")
    private String webhookSecret;

    @PostMapping
    public ResponseEntity<String> handleWebhook(
            @RequestHeader("X-Razorpay-Signature") String signature,
            @RequestBody String payload) throws Exception {

        boolean isValid = Utils.verifyWebhookSignature(
                payload,
                signature,
                webhookSecret
        );

        if (!isValid) {
            return ResponseEntity.badRequest().body("Invalid signature");
        }

        JSONObject json = new JSONObject(payload);
        String event = json.getString("event");

        if (event.equals("payment.captured")) {

            JSONObject paymentData = json.getJSONObject("payload")
                    .getJSONObject("payment")
                    .getJSONObject("entity");

            String razorpayOrderId = paymentData.getString("order_id");
            String paymentId = paymentData.getString("id");

            Payment payment = paymentRepository
                    .findByRazorpayOrderId(razorpayOrderId)
                    .orElseThrow();

            payment.setStatus(PaymentStatus.SUCCESS);
            payment.setRazorpayPaymentId(paymentId);
            paymentRepository.save(payment);

            FoodOrder order = payment.getOrder();
            order.setStatus(OrderStatus.CONFIRMED);
            orderRepository.save(order);
        }

        return ResponseEntity.ok("Success");
    }
}