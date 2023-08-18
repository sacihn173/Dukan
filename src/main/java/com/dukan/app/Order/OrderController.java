package com.dukan.app.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderSrv;

    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        return ResponseEntity.ok(orderSrv.createOrder(order));
    }

    @GetMapping("/find")
    public ResponseEntity<Order> getOrder(@RequestParam Integer orderId) {
        return ResponseEntity.ok(orderSrv.findById(orderId));
    }

}
